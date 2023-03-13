package com.web.gexam.action;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.util.MessageResources;
import org.springframework.web.struts.DispatchActionSupport;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.server.gexam.entity.TemplateAnswer;
import com.server.gexam.entity.TemplateImage;
import com.server.gexam.entity.TemplateQuestion;
import com.server.gexam.entity.User;
import com.util.DateTimeUtil;
import com.util.RandomUtil;

public abstract class CoreAction extends DispatchActionSupport {

	public static final String SESSION_USER = "SSUser";
	public static final String SESSION_PATH = "SSPathUpload";

	public static final String perpage = "50";

	public static final Locale defaultLocale = new Locale("en", "US");
	public static final String defaltDate = DateTimeUtil.parseOutputCalendar(DateTimeUtil.getSystemDate(), "dd/MM/yyyy", new Locale("en", "US"));

	public static final String encodingKeyID = "My-meeting-KeyID";
	public static final String encodingKeyPass = "My-meetingPasswd";

	// /Users/ONGz/Downloads/emeeting/, /etc/mymeeting/ ,/C:/etc/mymeeting/
	public static final String pathFile = "/etc/mymeeting/";

	protected void resetForm(DynaActionForm dynaForm, ActionMapping mapping, HttpServletRequest request) throws Exception {
		dynaForm.initialize(mapping);
		request.getSession().invalidate();
	}

	public static String getMessageResource(String bundle, String msgKey) throws Exception {
		return MessageResources.getMessageResources(bundle).getMessage(msgKey);
	}

	public static String getMessageGlobal(String msgKey) throws Exception {
		return MessageResources.getMessageResources("com.web.gexam.resource.global_th").getMessage(msgKey);
	}

	protected boolean checkString(String name, DynaActionForm dynaForm) throws Exception {
		boolean result = false;
		if (dynaForm.getString(name) != null && !dynaForm.getString(name).equals("")) {
			result = true;
		}
		return result;
	}

	protected boolean checkObject(String name, DynaActionForm dynaForm) throws Exception {
		boolean result = false;
		if (dynaForm.get(name) != null) {
			result = true;
		}
		return result;
	}

	protected void setObjectSession(HttpServletRequest request, String key, Object value) throws Exception {
		request.getSession().setAttribute(key, value);
	}

	protected Object getObjectSession(HttpServletRequest request, String key) throws Exception {
		return request.getSession().getAttribute(key);
	}

	protected void removeObjectSession(HttpServletRequest request, String key) throws Exception {
		request.getSession().removeAttribute(key);
	}

	protected User getUserSession(HttpServletRequest request) throws Exception {
		return (User) getObjectSession(request, SESSION_USER);
	}

	protected String setJsonView(List<?> objs, String dateFmt) {
		Gson gson = null;
		if (dateFmt != null && !dateFmt.equals("")) {
			gson = new GsonBuilder().setDateFormat(dateFmt).create();
		} else {
			gson = new Gson();
		}
		return gson.toJson(objs);
	}

	protected void setJSONResult(HttpServletRequest request, String value) throws Exception {
		request.setAttribute("jsonScript", value);
	}

	protected void setJSONReturn(HttpServletRequest request, boolean value) throws Exception {
		if (value)
			request.setAttribute("jsonScript", "[{\"action\": \"0\"}]");
		else
			request.setAttribute("jsonScript", "[{\"action\": \"1\"}]");
	}

	protected void setJSONReturn(HttpServletRequest request, String value) throws Exception {
		request.setAttribute("jsonScript", "[{\"action\": \"" + value + "\"}]");
	}

	protected ActionForward returnToJson(ActionMapping mapping) {
		return mapping.findForward("json_tag");
	}

	protected ActionForward mappingForward(ActionMapping mapping, HttpServletRequest request, String useParam, String useMode, String actionPath, String formName, String queryStr) {
		request.setAttribute("useParam", useParam);
		request.setAttribute("useMode", useMode);
		request.setAttribute("actionPath", actionPath);
		request.setAttribute("formName", formName);
		request.setAttribute("queryStr", queryStr == null ? "" : queryStr);
		return mapping.findForward("redirect");
	}

	protected String getClientIP(HttpServletRequest request) {
		try {
			return (request.getRemoteAddr() != null && !request.getRemoteAddr().equals("")) ? request.getRemoteAddr() : "-";
		} catch (Exception e) {
			e.printStackTrace();
			return "-";
		}
	}

	protected Object[] createAnswer(TemplateQuestion quest, TemplateAnswer ansTrue, TreeMap<Integer, TemplateImage> seqQMap, List<TemplateImage> imgList, boolean isAnswer) throws Exception {
		String answer = "";
		int data = 0;

		String numMin = "";
		String numMax = "";
		if (imgList.size() >= 4) {
			for (int i = 0; i < imgList.size() - 1; i++) {
				numMin += i + "";
				numMax += (i + 1) + "";
			}
			// numMin = "012";
			// numMax = "123";
		} else if (imgList.size() > 2 && imgList.size() < 4) {
			numMin = "01";
			numMax = "12";
		} else {
			numMin = "0";
			numMax = "0";
		}

		if (ansTrue.getConditionType().equals("01")) {
			// มากสุด
			if (isAnswer) {
				answer = ansTrue.getAnswer().replace("{param1}", seqQMap.get(Collections.max(seqQMap.keySet())).getImageName());
			} else {
				int seq = Integer.parseInt(RandomUtil.randomNumber(numMin, 1));
				answer = ansTrue.getAnswer().replace("{param1}", imgList.get(seq).getImageName());
			}
		} else if (ansTrue.getConditionType().equals("02")) {
			// น้อยสุด
			if (isAnswer) {
				answer = ansTrue.getAnswer().replace("{param1}", seqQMap.get(Collections.min(seqQMap.keySet())).getImageName());
			} else {
				int seq = Integer.parseInt(RandomUtil.randomNumber(numMax, 1));
				answer = ansTrue.getAnswer().replace("{param1}", imgList.get(seq).getImageName());
			}
		} else if (ansTrue.getConditionType().equals("03")) {
			// มากกว่า
			if (isAnswer) {
				int seq = Integer.parseInt(RandomUtil.randomNumber(numMax, 1));
				answer = (ansTrue.getAnswer().replace("{param1}", imgList.get(seq).getImageName())).replace("{param2}", imgList.get(seq - 1).getImageName());
			} else {
				int seq = Integer.parseInt(RandomUtil.randomNumber(numMin, 1));
				answer = (ansTrue.getAnswer().replace("{param1}", imgList.get(seq).getImageName())).replace("{param2}", imgList.get(seq + 1).getImageName());
			}
		} else if (ansTrue.getConditionType().equals("04")) {
			// น้อยกว่า
			if (isAnswer) {
				int seq = Integer.parseInt(RandomUtil.randomNumber(numMin, 1));
				answer = (ansTrue.getAnswer().replace("{param1}", imgList.get(seq).getImageName())).replace("{param2}", imgList.get(seq + 1).getImageName());
			} else {
				int seq = Integer.parseInt(RandomUtil.randomNumber(numMax, 1));
				answer = (ansTrue.getAnswer().replace("{param1}", imgList.get(seq).getImageName())).replace("{param2}", imgList.get(seq - 1).getImageName());
			}
		} else if (ansTrue.getConditionType().equals("05")) {
			// น้อยเป็นอันดับที่
			if (isAnswer) {
				int ord = Integer.parseInt(RandomUtil.randomNumber(numMin, 1));
				answer = (ansTrue.getAnswer().replace("{param1}", imgList.get(ord).getImageName())).replace("{order}", (ord + 1) + "");
			} else {
				int ord = Integer.parseInt(RandomUtil.randomNumber(numMax, 1));
				answer = (ansTrue.getAnswer().replace("{param1}", imgList.get(ord).getImageName())).replace("{order}", (4 - ord) + "");
			}
		} else if (ansTrue.getConditionType().equals("06")) {
			// มากเป็นอันดับที่
			if (isAnswer) {
				int ord = Integer.parseInt(RandomUtil.randomNumber(numMax, 1));
				answer = (ansTrue.getAnswer().replace("{param1}", imgList.get(ord).getImageName())).replace("{order}", (4 - ord) + "");
			} else {
				int ord = Integer.parseInt(RandomUtil.randomNumber(numMin, 1));
				answer = (ansTrue.getAnswer().replace("{param1}", imgList.get(ord).getImageName())).replace("{order}", (ord + 1) + "");
			}
		} else if (ansTrue.getConditionType().equals("07")) {
			// ผลรวมเท่ากับ
			int seq = Integer.parseInt(RandomUtil.randomNumber(numMin, 1));
			TemplateImage img1 = imgList.get(seq);
			TemplateImage img2 = imgList.get(seq + 1);
			int amount = img1.getAmount() + img2.getAmount();

			if (isAnswer) {
				answer = (((ansTrue.getAnswer().replace("{name1}", ansTrue.getName())).replace("{param1}", img1.getImageName())).replace("{param2}", img2.getImageName())).replace("{amount}", amount + "");
			} else {
				answer = (((ansTrue.getAnswer().replace("{name1}", ansTrue.getName())).replace("{param1}", img1.getImageName())).replace("{param2}", img2.getImageName())).replace("{amount}", (amount + (seq * 10)) + "");
			}
		} else if (ansTrue.getConditionType().equals("08")) {
			// ผลคูณเปรียบเทียบ
			if (isAnswer) {
				boolean check = true;
				int loopCheck = 0;
				while (check) {
					int seq = Integer.parseInt(RandomUtil.randomNumber(numMin, 1));
					int num1 = Integer.parseInt(RandomUtil.randomNumber("12345", 1));
					int num2 = Integer.parseInt(RandomUtil.randomNumber("12345", 1));
					TemplateImage img1 = imgList.get(seq);
					TemplateImage img2 = imgList.get(seq + 1);
					int total = img1.getAmount() * num1 + img2.getAmount() * num2;

					if (quest.getDefaultAmount() > 0) {
						if (total == quest.getDefaultAmount()) {
							check = false;
							answer = (((((ansTrue.getAnswer().replace("{param1}", img1.getImageName())).replace("{amount1}", num1 + "")).replace("{param2}", img2.getImageName())).replace("{amount2}", num2 + "")).replace("{unit1}", img1.getUnit())).replace("{unit2}", img2.getUnit());
						} else {
							loopCheck++;
						}
					} else {
						check = false;
						answer = (((((ansTrue.getAnswer().replace("{param1}", img1.getImageName())).replace("{amount1}", num1 + "")).replace("{param2}", img2.getImageName())).replace("{amount2}", num2 + "")).replace("{unit1}", img1.getUnit())).replace("{unit2}", img2.getUnit());
					}

					if (loopCheck == 10) {
						check = false;
					}

					data = total;
				}
			} else {
				boolean check = true;
				int loopCheck = 0;
				while (check) {
					int seq = Integer.parseInt(RandomUtil.randomNumber(numMin, 1));
					int num1 = Integer.parseInt(RandomUtil.randomNumber("1234", 1));
					int num2 = Integer.parseInt(RandomUtil.randomNumber("1234", 1));
					TemplateImage img1 = imgList.get(seq);
					TemplateImage img2 = imgList.get(seq + 1);
					int total = img1.getAmount() * num1 + img2.getAmount() * num2;

					if (quest.getDefaultAmount() > 0) {
						if (total != quest.getDefaultAmount()) {
							check = false;
							answer = (((((ansTrue.getAnswer().replace("{param1}", img1.getImageName())).replace("{amount1}", num1 + "")).replace("{param2}", img2.getImageName())).replace("{amount2}", num2 + "")).replace("{unit1}", img1.getUnit())).replace("{unit2}", img2.getUnit());
						} else {
							loopCheck++;
						}
					} else {
						check = false;
						answer = (((((ansTrue.getAnswer().replace("{param1}", img1.getImageName())).replace("{amount1}", num1 + "")).replace("{param2}", img2.getImageName())).replace("{amount2}", num2 + "")).replace("{unit1}", img1.getUnit())).replace("{unit2}", img2.getUnit());
					}

					data = total;

					if (loopCheck == 10) {
						check = false;
					}
				}
			}
		} else if (ansTrue.getConditionType().equals("09")) {
			// คงเหลือ
			if (quest.getCalculateType().equals("1")) { // คำนวณแบบสุ่ม
				int seq = Integer.parseInt(RandomUtil.randomNumber(numMax, 1));
				TemplateImage img1 = imgList.get(seq);
				int total = 0;
				if (img1.getAmount() <= 1000) {
					total = 1000;
				} else if (img1.getAmount() > 1000 && img1.getAmount() <= 2000) {
					total = 2000;
				}
				int amount = total - img1.getAmount();

				if (isAnswer) {
					answer = (((ansTrue.getAnswer().replace("{name1}", ansTrue.getName())).replace("{total}", total + "")).replace("{param1}", img1.getImageName())).replace("{amount}", amount + "");
				} else {
					answer = (((ansTrue.getAnswer().replace("{name1}", ansTrue.getName())).replace("{total}", total + "")).replace("{param1}", img1.getImageName())).replace("{amount}", (amount - 10) + "");
				}
			} else { // คำนวณแบบเรียง
				int total = 0;
				int amount1 = 0;
				int rate = 0;
				for (TemplateImage img : imgList) {
					if (img.getOptionType().equals("1"))
						total = img.getAmount();
					else if (img.getOptionType().equals("2"))
						amount1 = img.getAmount();
					else if (img.getOptionType().equals("3"))
						rate = img.getAmount();
				}

				DecimalFormat df = new DecimalFormat("#,##0");
				if (isAnswer) {
					int sum = (total - amount1) / rate;
					answer = ansTrue.getAnswer().replace("{amount1}", df.format(sum));
				} else {
					int[] numbs = ThreadLocalRandom.current().ints(1, 4 + 1).distinct().limit(1).toArray();
					int sum = 0;
					for (int a : numbs) {
						if (a == 1) {
							sum = total - amount1;
						} else if (a == 2) {
							sum = (total + amount1) / rate;
						} else if (a == 3) {
							sum = total / rate;
						} else if (a == 4) {
							sum = amount1 / rate;
						}
					}
					answer = ansTrue.getAnswer().replace("{amount1}", df.format(sum));
				}
			}
		} else if (ansTrue.getConditionType().equals("10")) {
			// ผลคูณรวมกันเท่ากับ
			int seq = Integer.parseInt(RandomUtil.randomNumber(numMin, 1));
			int num = Integer.parseInt(RandomUtil.randomNumber("2345", 1));
			TemplateImage img1 = imgList.get(seq);
			int total = img1.getAmount() * num;

			if (isAnswer) {
				answer = (((ansTrue.getAnswer().replace("{name1}", ansTrue.getName())).replace("{param1}", img1.getImageName())).replace("{amount1}", num + "")).replace("{total}", total + "");
			} else {
				answer = (((ansTrue.getAnswer().replace("{name1}", ansTrue.getName())).replace("{param1}", img1.getImageName())).replace("{amount1}", num + "")).replace("{total}", (total + (num * 10)) + "");
			}
		} else if (ansTrue.getConditionType().equals("11")) {
			// อัตราส่วนเปรียบเทียบ
			int seq1 = 0;
			TemplateImage img1 = imgList.get(seq1);
			int num1 = 1;
			int amount1 = (img1.getAmount() / img1.getItem()) * num1;

			boolean check = true;
			int loopCheck = 0;
			while (check) {

				int seq2 = Integer.parseInt(RandomUtil.randomNumber(numMax, 1));
				if (seq2 == seq1)
					continue;

				TemplateImage img2 = imgList.get(seq2);
				int num2 = Integer.parseInt(RandomUtil.randomNumber("12", 1));
				int amount2 = (img2.getAmount() / img2.getItem()) * num2;

				if (isAnswer) {
					if (amount1 == amount2) {
						check = false;
						answer = (((((ansTrue.getAnswer().replace("{param1}", img1.getImageName())).replace("{amount1}", num1 + "")).replace("{unit1}", img1.getUnit())).replace("{param2}", img2.getImageName())).replace("{amount2}", num2 + "")).replace("{unit2}", img2.getUnit());
					} else {
						loopCheck++;
					}
				} else {
					if (amount1 != amount2) {
						check = false;
						answer = (((((ansTrue.getAnswer().replace("{param1}", img1.getImageName())).replace("{amount1}", num1 + "")).replace("{unit1}", img1.getUnit())).replace("{param2}", img2.getImageName())).replace("{amount2}", num2 + "")).replace("{unit2}", img2.getUnit());
					} else {
						loopCheck++;
					}
				}

				if (loopCheck == 10) {
					check = false;
				}

			}
		} else if (ansTrue.getConditionType().equals("12")) {
			// อัตราส่วนส่วนต่าง
			int seq1 = Integer.parseInt(RandomUtil.randomNumber(numMax, 1));
			TemplateImage img1 = imgList.get(seq1);
			int num1 = Integer.parseInt(RandomUtil.randomNumber("12", 1));
			int amount1 = (img1.getAmount() / img1.getItem()) * num1;

			boolean check = true;
			while (check) {
				int seq2 = Integer.parseInt(RandomUtil.randomNumber(numMax, 1));
				if (seq2 == seq1)
					continue;

				TemplateImage img2 = imgList.get(seq2);
				int num2 = Integer.parseInt(RandomUtil.randomNumber("12", 1));
				int amount2 = (img2.getAmount() / img2.getItem()) * num2;

				int total = amount1 + amount2;

				if (!isAnswer) {
					total = amount1 + (img2.getAmount() / img2.getItem()) * (num2 + 1);
				}

				answer = (((((ansTrue.getAnswer().replace("{param1}", img1.getImageName())).replace("{amount1}", num1 + "")).replace("{unit1}", img1.getUnit()).replace("{param2}", img2.getImageName())).replace("{amount2}", num2 + "")).replace("{unit2}", img2.getUnit())).replace("{total}", total + "");

				check = false;

			}

		} else if (ansTrue.getConditionType().equals("13")) {
			// น้อยไปมาก
			if (isAnswer) {
				for (TemplateImage img : imgList) {
					answer += img.getImageName() + "  ";
				}

				answer = answer.substring(0, answer.length() - 2);
			} else {
				boolean check = true;
				while (check) {
					int[] nums = null;
					if (imgList.size() == 3) {
						nums = ThreadLocalRandom.current().ints(0, 3).distinct().limit(3).toArray();
					} else if (imgList.size() == 4) {
						nums = ThreadLocalRandom.current().ints(0, 4).distinct().limit(4).toArray();
					}

					if (nums[0] == 0)
						continue;

					for (int i = 0; i < nums.length; i++) {
						TemplateImage img = imgList.get(nums[i]);
						answer += img.getImageName() + "  ";
					}

					answer = answer.substring(0, answer.length() - 2);
					check = false;
				}

			}
		}

		return new Object[] { answer, data };
	}

}
