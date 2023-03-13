package com.web.gexam.action;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.upload.FormFile;

import com.server.gexam.entity.Constant;
import com.server.gexam.entity.ExamQuestion;
import com.server.gexam.entity.ExamQuestionAnswer;
import com.server.gexam.entity.ExamQuestionImage;
import com.server.gexam.entity.ExamSuite;
import com.server.gexam.entity.ExamSuiteQuestion;
import com.server.gexam.entity.Indicator;
import com.server.gexam.entity.TemplateAnswer;
import com.server.gexam.entity.TemplateImage;
import com.server.gexam.entity.TemplateQuestion;
import com.server.gexam.service.ConstantService;
import com.server.gexam.service.ExamQuestionAnswerService;
import com.server.gexam.service.ExamQuestionImageService;
import com.server.gexam.service.ExamQuestionService;
import com.server.gexam.service.ExamSuiteQuestionService;
import com.server.gexam.service.ExamSuiteService;
import com.server.gexam.service.IndicatorService;
import com.server.gexam.service.TemplateAnswerService;
import com.server.gexam.service.TemplateImageService;
import com.server.gexam.service.TemplateQuestionService;
import com.util.DateTimeUtil;

public class MasterAction extends CoreAction {

	private ExamQuestionService examQuestionService;
	private ExamQuestionImageService examQuestionImageService;
	private ExamQuestionAnswerService examQuestionAnswerService;
	private TemplateQuestionService templateQuestionService;
	private TemplateImageService templateImageService;
	private TemplateAnswerService templateAnswerService;
	private IndicatorService indicatorService;
	private ConstantService constantService;
	private ExamSuiteService examSuiteService;
	private ExamSuiteQuestionService examSuiteQuestionService;

	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			DynaActionForm dynaForm = (DynaActionForm) form;
			// resetForm(dynaForm, mapping, request);

			// List results = examQuestionService.getExamQuestionList(null,
			// null);
			dynaForm.set("resultList", null);

			dynaForm.set("comboIndicator", indicatorService.getAll());

			Constant constant = constantService.getItem(1);
			setObjectSession(request, SESSION_PATH, constant.getUploadPath());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward("MA01");
	}

	public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			DynaActionForm dynaForm = (DynaActionForm) form;

			List results = examQuestionService.getExamQuestionList(dynaForm.getString("indicatorIdSearch"), null, dynaForm.getString("questionSearch"));
			dynaForm.set("resultList", results);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward("MA01");
	}

	public ActionForward initAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			DynaActionForm dynaForm = (DynaActionForm) form;

			// List<TemplateQuestion> results =
			// templateQuestionService.getTemplateQuestionList(null, null,
			// null);
			dynaForm.set("resultList", null);

			dynaForm.set("sampleList", null);
			dynaForm.set("checkIds", null);
			dynaForm.set("questionAmount", null);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward("MA02");
	}

	public ActionForward searchAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			DynaActionForm dynaForm = (DynaActionForm) form;

			List<TemplateQuestion> results = templateQuestionService.getTemplateQuestionList(dynaForm.getString("indicatorIdAdd"), null, null);
			dynaForm.set("resultList", results);

			dynaForm.set("sampleList", null);
			dynaForm.set("checkIds", null);
			dynaForm.set("questionAmount", null);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward("MA02");
	}

	public ActionForward sample(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			DynaActionForm dynaForm = (DynaActionForm) form;

			String questionAmount = dynaForm.getString("questionAmount");
			String[] checkIds = (String[]) dynaForm.get("checkIds");

			List<ExamQuestion> examQuestList = new ArrayList<ExamQuestion>();

			if (checkIds != null && checkIds.length > 0) {

				int questAmount = questionAmount != null && !questionAmount.equals("") ? Integer.parseInt(questionAmount) : 1;

				for (String id : checkIds) {

					int questionId = Integer.parseInt(id);

					// คำถาม
					TemplateQuestion quest = templateQuestionService.getItem(questionId);

					for (int loop = 0; loop < questAmount; loop++) {

						// ตัวแปรชื่อสำหรับคำตอบ (ถ้ามี)
						String[] names = null;
						if (quest.getName4() != null && !quest.getName4().equals("")) {
							names = new String[] { quest.getName1(), quest.getName2(), quest.getName3(), quest.getName4() };
						} else if (quest.getName3() != null && !quest.getName3().equals("")) {
							names = new String[] { quest.getName1(), quest.getName2(), quest.getName3() };
						} else if (quest.getName2() != null && !quest.getName2().equals("")) {
							names = new String[] { quest.getName1(), quest.getName2() };
						}

						// ค้นหาตัวเลือกคำถาม เช่น รูป, ข้อความ
						List<TemplateImage> images = templateImageService.getTemplateImageList(questionId + "", null);

						// 1. เรียงลำดับตัวเลือกคำถาม
						HashMap<Integer, TemplateImage> qMap = new HashMap<Integer, TemplateImage>();
						int rQ = 0;
						int last = 0;
						for (TemplateImage img : images) {
							qMap.put(rQ++, img);
						}
						last = rQ;

						// 2. สุ่มตัวเลือกคำถาม
						int[] quests = ThreadLocalRandom.current().ints(0, last).distinct().limit(images.size()).toArray();
						int[] amounts = new int[images.size()];
						if (quest.getParameter2() > 0)
							amounts = ThreadLocalRandom.current().ints(quest.getParameter1(), quest.getParameter2() + 1).distinct().limit(images.size()).toArray();

						int[] rates = new int[images.size()];
						if (quest.getRateType().equals("Y")) { // คำนวนอัตราส่วน
							int[] numbs = ThreadLocalRandom.current().ints(3, 21).limit(20).toArray();
							int r = 0;
							for (int ra : numbs) {
								if (ra % 3 == 0 || ra % 4 == 0 || ra % 5 == 0) {
									rates[r] = ra;

									if (r == images.size() - 1)
										break;

									r++;
								}
							}
							// เซตค่าที่เหมือนค่าแรก
							rates[images.size() - 1] = rates[1];
						}

						// เรียงลำดับตัวเลือกคำถาม
						TreeMap<Integer, TemplateImage> seqQMap = new TreeMap<Integer, TemplateImage>();

						// set ตัวเลือกคำถาม
						List<ExamQuestionImage> examImageList = new ArrayList<ExamQuestionImage>();

						int a = 0;
						if (quest.getRandomType().equals("Y")) { // สุ่ม
							int remain = 0;
							for (int j : quests) {
								if (qMap.containsKey(j)) {
									int amount = amounts[a];

									TemplateImage img = qMap.get(j);

									ExamQuestionImage examImg = new ExamQuestionImage();
									examImg.setImageId(img.getImageId());
									examImg.setImageOnly("N");

									if (quest.getRateType().equals("Y")) { // คูณอัตราส่วน
										img.setItem(amount);
										img.setAmount(amount * rates[a]);
									} else {
										if (remain > 0)
											amount = (quest.getDefaultAmount() - remain) / 2;

										int[] numbs = ThreadLocalRandom.current().ints(quest.getParameter1(), quest.getParameter2() + 1).distinct().limit(quest.getParameter2() + 1 - quest.getParameter1()).toArray();
										for (int ra : numbs) {
											if (quest.getDefaultAmount() > 0) {
												int nRemain = remain == 0 ? quest.getDefaultAmount() : remain;

												if (quest.getDefaultAmount() > 1000) {
													if (ra % 50 == 0 && nRemain % ra == 0 && !seqQMap.containsKey(ra)) {
														amount = ra;

														remain = quest.getDefaultAmount() - amount * 2;
														if (remain <= 0)
															remain = quest.getDefaultAmount() - amount;

														break;
													}
												} else {
													if (nRemain % ra == 0 && !seqQMap.containsKey(ra)) {
														amount = ra;

														remain = quest.getDefaultAmount() - amount * 2;
														if (remain <= 0)
															remain = quest.getDefaultAmount() - amount;

														break;
													}
												}

											}
										}

										img.setAmount(amount);
									}

									if (!quest.getConditionType().equals("3") && !quest.getConditionType().equals("4")) {
										if (quest.getRateType().equals("Y")) { // คำนวนอัตราส่วน
											examImg.setImageName(img.getImageName() + " " + img.getItem() + " " + img.getUnit());
											examImg.setUnitName(img.getUnitName());
											examImg.setAmount(img.getAmount());
											examImg.setUnit(img.getUnit2());
										} else {
											examImg.setImageName(img.getImageName());
											examImg.setUnitName(img.getUnitName());
											examImg.setAmount(amount);
											examImg.setUnit(img.getUnit());
										}
									} else {
										examImg.setImageName(img.getImageName());
										examImg.setAmount(amount);
										examImg.setUnit(img.getUnit());
									}
									seqQMap.put(amount, img);

									a++;

									examImg.setCreateBy(getUserSession(request).getUsername());
									examImg.setCreateDate(DateTimeUtil.getSystemDate());
									examImageList.add(examImg);
								}
							}
						} else { // ไม่สุ่ม
							int amount1 = 0;
							int amount2 = 0;
							int remain = 0;
							for (TemplateImage img : images) {

								int amount = amounts[a];

								ExamQuestionImage examImg = new ExamQuestionImage();
								examImg.setImageId(img.getImageId());
								examImg.setImageOnly("N");

								if (quest.getRateType().equals("Y")) { // คูณอัตราส่วน
									img.setItem(amount);
									img.setAmount(amount * rates[a]);
								} else {
									if (img.getOptionType().equals("1")) { // จำนวนเต็ม
										amount1 = 17000;
										if (quest.getDefaultAmount() > 0) {
											if (remain > 0)
												amount1 = (quest.getDefaultAmount() - remain) / 3;
										}
										img.setAmount(amount1); // default

										int[] numbs = ThreadLocalRandom.current().ints(img.getParameter1(), img.getParameter2() + 1).distinct().limit(img.getParameter2() + 1 - img.getParameter1()).toArray();
										for (int ra : numbs) {
											if (quest.getDefaultAmount() > 0) {
												int nRemain = remain == 0 ? quest.getDefaultAmount() : remain;
												if (ra % 100 == 0 && !seqQMap.containsKey(ra) && nRemain % ra == 0) {
													img.setAmount(ra);
													amount1 = ra;

													if (remain == 0)
														remain = quest.getDefaultAmount() - amount1 * 3;

													break;
												}
											} else {
												if (ra % 100 == 0 && !seqQMap.containsKey(ra)) {
													img.setAmount(ra);
													amount1 = ra;
													break;
												}
											}
										}

										// set ค่าใหม่ ถ้าค่าเท่าเดิม
										if (seqQMap.containsKey(img.getAmount())) {
											if (quest.getDefaultAmount() > 0)
												img.setAmount(img.getAmount() + quest.getDefaultAmount() / 10);
											else
												img.setAmount(img.getAmount() + 100);
										}

										amount = img.getAmount();
									} else if (img.getOptionType().equals("2")) { // หักลบ
										amount2 = 8000;
										img.setAmount(amount2); // default

										int[] numbs = ThreadLocalRandom.current().ints(img.getParameter1(), img.getParameter2() + 1).distinct().limit(100).toArray();
										for (int ra : numbs) {
											if (ra % 100 == 0) {
												img.setAmount(ra);
												amount2 = ra;
												break;
											}
										}

										amount = img.getAmount();
									} else if (img.getOptionType().equals("3")) { // แบ่งส่วน
										int[] numbs = ThreadLocalRandom.current().ints(img.getParameter1(), img.getParameter2() + 1).distinct().limit(3).toArray();
										int total = amount1 - amount2;

										for (int ra : numbs) {
											if (total % ra == 0) {
												img.setAmount(ra);
												break;
											}
										}

										amount = img.getAmount();
									} else {
										img.setAmount(amount);
									}
								}

								if (!quest.getConditionType().equals("3") && !quest.getConditionType().equals("4")) {
									if (quest.getRateType().equals("Y")) { // คำนวนอัตราส่วน
										examImg.setImageName(img.getImageName() + " " + img.getItem() + " " + img.getUnit());
										examImg.setUnitName(img.getUnitName());
										examImg.setAmount(img.getAmount());
										examImg.setUnit(img.getUnit2());
									} else {
										examImg.setImageName(img.getImageName());
										examImg.setUnitName(img.getUnitName());
										examImg.setAmount(amount);
										examImg.setUnit(img.getUnit());
									}
								} else {
									examImg.setImageName(img.getImageName());
									examImg.setAmount(amount);
									examImg.setUnit(img.getUnit());
								}
								seqQMap.put(amount, img);

								a++;

								examImg.setCreateBy(getUserSession(request).getUsername());
								examImg.setCreateDate(DateTimeUtil.getSystemDate());
								examImageList.add(examImg);
							}
						}

						// set คำถาม
						ExamQuestion examQuestion = new ExamQuestion();
						examQuestion.setIndicator(quest.getIndicator());
						examQuestion.setTitle(quest.getTitle());
						examQuestion.setQuestion(quest.getQuestion());
						examQuestion.setScore(1);
						examQuestion.setQuestionType(quest.getTemplateType());
						examQuestion.setRateType(quest.getRateType());
						examQuestion.setCreateType("1");
						examQuestion.setCreateBy(getUserSession(request).getUsername());
						examQuestion.setCreateDate(DateTimeUtil.getSystemDate());

						// เพ่ิมตัวเลือกคำถาม ลงในคำถาม
						examQuestion.setImageList(examImageList);

						// end คำถาม

						// 3. สุ่มคำตอบ
						List<TemplateAnswer> answers = templateAnswerService.getTemplateAnswerList(questionId + "", null);

						HashMap<Integer, TemplateAnswer> aMap = new HashMap<Integer, TemplateAnswer>();
						int rA = 0;
						for (TemplateAnswer ans : answers) {

							TemplateAnswer nAns = new TemplateAnswer();
							nAns.setTemplateQuestion(ans.getTemplateQuestion());
							nAns.setAnswer(ans.getAnswer());
							nAns.setConditionType(ans.getConditionType());
							if (names != null && names.length > 0)
								nAns.setName(names[rA]);

							aMap.put(rA, nAns);

							if (rA == 3)
								break;

							rA++;
						}

						// ถ้าคำตอบไม่ครบ 4 ให้ระบบเพิ่มให้ครบ 4 ตัวเลือก
						if (rA < 3) {
							for (int i = rA; i <= 3;) {
								if (rA < 4) {
									for (TemplateAnswer ans : answers) {

										TemplateAnswer nAns = new TemplateAnswer();
										nAns.setTemplateQuestion(ans.getTemplateQuestion());
										nAns.setAnswer(ans.getAnswer());
										nAns.setConditionType(ans.getConditionType());
										if (names != null && names.length > 0)
											nAns.setName(names[rA]);

										aMap.put(rA, nAns);

										rA++;

										if (rA == 4)
											break;
									}
								} else {
									break;
								}
							}
						}

						// ตัวแปรคำตอบที่ถูกต้อง
						TemplateAnswer ansTrue = null;
						// ตัวแปรคำตอบที่ผิด
						List<TemplateAnswer> ansFalseList = new ArrayList<TemplateAnswer>();
						// สุ่มคำตอบ
						int[] randomNums = ThreadLocalRandom.current().ints(0, 4).distinct().limit(4).toArray();
						for (int i = 0; i < randomNums.length; i++) {
							if (aMap.containsKey(randomNums[i])) {
								TemplateAnswer ans = aMap.get(randomNums[i]);

								if (i == 0)
									ansTrue = ans; // ถูก
								else
									ansFalseList.add(ans); // ผิด
							}
						}

						// แปลง map -> list
						List<TemplateImage> imgList = new ArrayList<TemplateImage>(seqQMap.values());

						// เก็บค่าเพื่อสุ่มใหม่
						TreeMap<Integer, Object[]> ansMap = new TreeMap<Integer, Object[]>();

						if (!quest.getConditionType().equals("3") && !quest.getConditionType().equals("4")) {

							boolean check = true;
							int iCheck = 0;
							while (check) {

								if (iCheck == 5) {
									ansTrue = null;
									ansFalseList = new ArrayList<TemplateAnswer>();
									// สุ่มคำตอบใหม่
									int[] nRandomNums = ThreadLocalRandom.current().ints(0, 4).distinct().limit(4).toArray();
									for (int i = 0; i < nRandomNums.length; i++) {
										if (aMap.containsKey(nRandomNums[i])) {
											TemplateAnswer ans = aMap.get(nRandomNums[i]);

											if (i == 0)
												ansTrue = ans; // ถูก
											else
												ansFalseList.add(ans); // ผิด
										}
									}

									iCheck = 0;
								}

								ansMap = new TreeMap<Integer, Object[]>();

								int choice = 0;

								String answerT = (String) createAnswer(quest, ansTrue, seqQMap, imgList, true)[0];

								if (answerT != null && !answerT.equals("")) {
									ansMap.put(choice, new Object[] { answerT, "Y" });
									choice++;
								}

								HashMap<String, String> checkDupMap = new HashMap<String, String>();
								for (TemplateAnswer ansFalse : ansFalseList) {
									String answerF = (String) createAnswer(quest, ansFalse, seqQMap, imgList, false)[0];

									if (answerF != null && !answerF.equals("")) {
										if (!checkDupMap.containsKey(answerF)) {
											ansMap.put(choice, new Object[] { answerF, "N" });
											choice++;

											checkDupMap.put(answerF, answerF);
										}
									}
								}

								if (ansMap.size() == 4)
									check = false;
								else {
									iCheck++;
								}
							}

						} else {
							// ใช้สำหรับเรียงลำดับ default (น้อยไปมาก)
							TreeMap<Integer, String> rsMap = new TreeMap<Integer, String>();

							boolean checkRs = true;
							int iCheck = 0;
							while (checkRs) {

								if (iCheck == 5) {
									ansTrue = null;
									ansFalseList = new ArrayList<TemplateAnswer>();
									// สุ่มคำตอบใหม่
									int[] nRandomNums = ThreadLocalRandom.current().ints(0, 4).distinct().limit(4).toArray();
									for (int i = 0; i < nRandomNums.length; i++) {
										if (aMap.containsKey(nRandomNums[i])) {
											TemplateAnswer ans = aMap.get(nRandomNums[i]);

											if (i == 0)
												ansTrue = ans; // ถูก
											else
												ansFalseList.add(ans); // ผิด
										}
									}

									iCheck = 0;
								}

								rsMap = new TreeMap<Integer, String>();
								Object[] objT = createAnswer(quest, ansTrue, seqQMap, imgList, true);
								String answer1 = String.valueOf(objT[0]);

								if (answer1 != null && !answer1.equals(""))
									rsMap.put(Integer.parseInt(String.valueOf(objT[1])), String.valueOf(objT[0]));

								for (TemplateAnswer ansFalse : ansFalseList) {
									Object[] objF = createAnswer(quest, ansFalse, seqQMap, imgList, true);
									String answer2 = String.valueOf(objF[0]);

									if (answer2 != null && !answer2.equals(""))
										rsMap.put(Integer.parseInt(String.valueOf(objF[1])), String.valueOf(objF[0]));
								}

								if (rsMap.size() == 4)
									checkRs = false;
								else {
									iCheck++;
								}
							}

							int choice = 0;

							for (Integer key : rsMap.keySet()) {
								String answer = rsMap.get(key);

								if (quest.getConditionType().equals("3")) { // น้อยที่สุด
									ansMap.put(choice, new Object[] { answer, choice == 0 ? "Y" : "N" });
								} else if (quest.getConditionType().equals("4")) { // มากที่สุด
									ansMap.put(choice, new Object[] { answer, choice == 3 ? "Y" : "N" });
								}

								choice++;
							}

						}

						// set คำตอบ
						List<ExamQuestionAnswer> answerList = new ArrayList<ExamQuestionAnswer>();

						int[] randomChoices = ThreadLocalRandom.current().ints(0, 4).distinct().limit(4).toArray();
						for (int ch = 0; ch < randomChoices.length; ch++) {
							Object[] obj = ansMap.get(randomChoices[ch]);

							ExamQuestionAnswer answer = new ExamQuestionAnswer();
							answer.setSeq(ch + 1);
							answer.setAnswer(String.valueOf(obj[0]));
							answer.setAnswerYn(String.valueOf(obj[1]));
							answer.setCreateBy(getUserSession(request).getUsername());
							answer.setCreateDate(DateTimeUtil.getSystemDate());
							answerList.add(answer);
						}

						// เพิ่มคำตอบ ลงในคำถาม
						examQuestion.setAnswerList(answerList);

						examQuestList.add(examQuestion);

					}

				} // end loop id
			}

			dynaForm.set("sampleList", examQuestList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward("MA03");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			DynaActionForm dynaForm = (DynaActionForm) form;

			List<ExamQuestion> examQuestList = (List<ExamQuestion>) dynaForm.get("sampleList");
			examQuestionService.saveExamQuestion(examQuestList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return mappingForward(mapping, request, "mode", "init", "master.htm", "masterForm", null);
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			DynaActionForm dynaForm = (DynaActionForm) form;
			examQuestionService.removeItem(Integer.parseInt(dynaForm.getString("id")));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mappingForward(mapping, request, "mode", "search", "master.htm", "masterForm", null);
	}

	public ActionForward initEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			DynaActionForm dynaForm = (DynaActionForm) form;

			ExamQuestion question = examQuestionService.getItem(Integer.parseInt(dynaForm.getString("id")));

			List<ExamQuestionAnswer> answerList = examQuestionAnswerService.getExamQuestionAnswerList(null, dynaForm.getString("id"));
			List<ExamQuestionImage> imageList = examQuestionImageService.getExamQuestionImageList(null, dynaForm.getString("id"));

			question.setAnswerList(answerList);
			question.setImageList(imageList);

			request.setAttribute("PExamQuestion", question);

			dynaForm.set("title", question.getTitle());
			dynaForm.set("question", question.getQuestion());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward("MA04");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			DynaActionForm dynaForm = (DynaActionForm) form;
			ExamQuestion question = examQuestionService.getItem(Integer.parseInt(dynaForm.getString("id")));
			question.setTitle(dynaForm.getString("title"));
			question.setQuestion(dynaForm.getString("question"));
			question.setUpdateBy(getUserSession(request).getUsername());
			question.setUpdateDate(DateTimeUtil.getSystemDate());

			examQuestionService.updateItem(question);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return mappingForward(mapping, request, "mode", "search", "master.htm", "masterForm", null);
	}

	// เพิ่มแบบกำหนดเอง
	public ActionForward initManualQuestion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			DynaActionForm dynaForm = (DynaActionForm) form;
			resetQuestionForm(dynaForm);

			dynaForm.set("comboIndicator", indicatorService.getAll());

			// HttpSession session = request.getSession();
			// session.setAttribute(SESSION_PATH, constant.getUploadPath());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward("MA05");
	}

	public ActionForward saveManualQuestion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			DynaActionForm dynaForm = (DynaActionForm) form;

			ExamQuestion entity = new ExamQuestion();
			entity.setIndicator(new Indicator(Integer.parseInt(dynaForm.getString("indicatorId"))));
			entity.setTitle(dynaForm.getString("title"));
			entity.setQuestion(dynaForm.getString("question"));
			entity.setQuestionType(dynaForm.getString("templateType"));
			entity.setRateType(dynaForm.getString("rateType"));
			entity.setScore(1);
			entity.setCreateType("2");
			entity.setCreateBy(getUserSession(request).getUsername());
			entity.setCreateDate(DateTimeUtil.getSystemDate());
			entity.setUpdateBy(getUserSession(request).getUsername());
			entity.setUpdateDate(DateTimeUtil.getSystemDate());
			ExamQuestion merge = examQuestionService.mergeItem(entity);

			dynaForm.set("id", merge.getId() + "");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return mappingForward(mapping, request, "mode", "initManualImage", "master.htm", "masterForm", null);
	}

	public ActionForward editManualQuestion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			DynaActionForm dynaForm = (DynaActionForm) form;

			ExamQuestion entity = examQuestionService.getItem(Integer.parseInt(dynaForm.getString("id")));
			entity.setTitle(dynaForm.getString("title"));
			entity.setQuestion(dynaForm.getString("question"));
			entity.setUpdateDate(DateTimeUtil.getSystemDate());
			examQuestionService.updateItem(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mappingForward(mapping, request, "mode", "initManualImage", "master.htm", "masterForm", null);
	}

	public ActionForward removeManualQuestion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			DynaActionForm dynaForm = (DynaActionForm) form;
			examQuestionService.removeItem(Integer.parseInt(dynaForm.getString("id")));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mappingForward(mapping, request, "mode", "init", "master.htm", "masterForm", null);
	}

	public ActionForward initManualImage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			DynaActionForm dynaForm = (DynaActionForm) form;

			String id = dynaForm.getString("id");
			if (id != null && !id.equals("")) {

				// question
				ExamQuestion item = examQuestionService.getItem(Integer.parseInt(id));
				dynaForm.set("templateType", item.getQuestionType()); // 1,2,3

				request.setAttribute("PQuestion", item);
				dynaForm.set("question", item.getQuestion());
				dynaForm.set("title", item.getTitle());

				// image
				List<ExamQuestionImage> imageList = examQuestionImageService.getExamQuestionImageList(null, id);
				dynaForm.set("imageList", imageList);

				request.setAttribute("PImageSize", imageList.size());

				// answer
				List<ExamQuestionAnswer> answerList = examQuestionAnswerService.getExamQuestionAnswerList(null, id);
				dynaForm.set("answerList", answerList);

				request.setAttribute("PAnswerSize", answerList.size());
			}

			resetImageForm(dynaForm);

			String msg = request.getParameter("er");
			if (msg != null && !msg.equals("")) {
				request.setAttribute("msgError", msg);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward("MA06");
	}

	public ActionForward saveManualImage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;

		try {
			DynaActionForm dynaForm = (DynaActionForm) form;

			String templateType = dynaForm.getString("templateType");

			ExamQuestionImage entity = new ExamQuestionImage();
			entity.setExamQuestion(new ExamQuestion(Integer.parseInt(dynaForm.getString("id"))));

			if (templateType.equals("1")) { // รูปภาพ
				FormFile img = (FormFile) dynaForm.get("uploadFile1");

				boolean checkImg = false;
				String ext = "";
				if (img.getFileName() != null && img.getFileName().lastIndexOf(".") != -1) {
					ext = img.getFileName().substring(img.getFileName().lastIndexOf("."));
					ext = ext.replace(".", "");

					if (ext.equalsIgnoreCase("jpg") || ext.equalsIgnoreCase("jpeg") || ext.equalsIgnoreCase("png")) {
						checkImg = true;
					}
				}

				String findId = "";
				if (checkImg) {
					Constant constant = constantService.getItem(1);

					String filePath = constant.getUploadPath();
					findId = DateTimeUtil.parseOutputCalendar(new Date(), "yyyyMMddHHmmssSSS", new Locale("en", "US")) + "." + ext;

					fos = new FileOutputStream(filePath + findId);
					bos = new BufferedOutputStream(fos);
					bos.write(img.getFileData());
				} else {
					return mappingForward(mapping, request, "mode", "initManualImage", "master.htm", "masterForm", "&er=ext");
				}

				entity.setImageId(findId);
			}

			entity.setImageOnly(dynaForm.getString("imageOnly"));
			entity.setImageName(dynaForm.getString("fileName1"));
			entity.setUnitName(dynaForm.getString("unitName1"));
			entity.setAmount(Integer.parseInt(dynaForm.getString("amount1")));
			entity.setUnit(dynaForm.getString("unit1"));
			entity.setCreateBy(getUserSession(request).getUsername());
			entity.setCreateDate(DateTimeUtil.getSystemDate());
			entity.setUpdateBy(getUserSession(request).getUsername());
			entity.setUpdateDate(DateTimeUtil.getSystemDate());
			examQuestionImageService.saveItem(entity);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null)
				fos.close();

			if (bos != null)
				bos.close();
		}

		return mappingForward(mapping, request, "mode", "initManualImage", "master.htm", "masterForm", null);
	}

	public ActionForward removeManualImage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			DynaActionForm dynaForm = (DynaActionForm) form;
			ExamQuestionImage entity = examQuestionImageService.getItem(Integer.parseInt(dynaForm.getString("questImageId")));

			if (entity.getImageId() != null && !entity.getImageId().equals("")) {
				String path = (String) getObjectSession(request, SESSION_PATH);
				String fileName = path + entity.getImageId();

				File file = new File(fileName);
				if (file.exists()) {
					file.delete();
				}
			}

			examQuestionImageService.removeItem(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mappingForward(mapping, request, "mode", "initManualImage", "master.htm", "masterForm", null);
	}

	public ActionForward saveManualAnswer(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			DynaActionForm dynaForm = (DynaActionForm) form;

			ExamQuestionAnswer entity = new ExamQuestionAnswer();
			entity.setExamQuestion(new ExamQuestion(Integer.parseInt(dynaForm.getString("id"))));
			entity.setSeq(Integer.parseInt(dynaForm.getString("answerSeq")));
			entity.setAnswer(dynaForm.getString("answerName"));
			entity.setAnswerYn(dynaForm.getString("answerYN"));
			entity.setCreateBy(getUserSession(request).getUsername());
			entity.setCreateDate(DateTimeUtil.getSystemDate());
			entity.setUpdateBy(getUserSession(request).getUsername());
			entity.setUpdateDate(DateTimeUtil.getSystemDate());
			examQuestionAnswerService.saveItem(entity);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return mappingForward(mapping, request, "mode", "initManualImage", "master.htm", "masterForm", null);
	}

	public ActionForward removeManualAnswer(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			DynaActionForm dynaForm = (DynaActionForm) form;
			examQuestionAnswerService.removeItem(Integer.parseInt(dynaForm.getString("questAnswerId")));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mappingForward(mapping, request, "mode", "initManualImage", "master.htm", "masterForm", null);
	}

	private void resetQuestionForm(DynaActionForm dynaForm) {
		dynaForm.set("id", null);
		dynaForm.set("indicatorId", null);
		dynaForm.set("templateName", null);
		dynaForm.set("title", null);
		dynaForm.set("question", null);
		dynaForm.set("conditionType", "1");
		dynaForm.set("templateType", "1");
		dynaForm.set("randomType", "Y");
		dynaForm.set("rateType", "N");
		dynaForm.set("calculateType", "1");
		dynaForm.set("parameter1", "0");
		dynaForm.set("parameter2", "0");
		dynaForm.set("defaultAmount", "0");

		resetImageForm(dynaForm);
	}

	private void resetImageForm(DynaActionForm dynaForm) {
		dynaForm.set("questImageId", null);
		dynaForm.set("uploadFile1", null);
		dynaForm.set("uploadFile2", null);
		dynaForm.set("uploadFile3", null);
		dynaForm.set("uploadFile4", null);
		dynaForm.set("uploadFileName1", null);
		dynaForm.set("uploadFileName2", null);
		dynaForm.set("uploadFileName3", null);
		dynaForm.set("uploadFileName4", null);
		dynaForm.set("fileId1", null);
		dynaForm.set("fileId2", null);
		dynaForm.set("fileId3", null);
		dynaForm.set("fileId4", null);
		dynaForm.set("fileName1", null);
		dynaForm.set("fileName2", null);
		dynaForm.set("fileName3", null);
		dynaForm.set("fileName4", null);
		dynaForm.set("unitName1", null);
		dynaForm.set("unitName2", null);
		dynaForm.set("unitName3", null);
		dynaForm.set("unitName4", null);
		dynaForm.set("unit1", null);
		dynaForm.set("unit2", null);
		dynaForm.set("unit3", null);
		dynaForm.set("unit4", null);
		dynaForm.set("amount1", "0");
		dynaForm.set("amount2", "0");
		dynaForm.set("amount3", "0");
		dynaForm.set("amount4", "0");
		dynaForm.set("imageOnly", "N");

		dynaForm.set("questAnswerId", null);
		dynaForm.set("answerName", null);
		dynaForm.set("answerSeq", null);
		dynaForm.set("answerYN", "N");

	}

	// ชุดข้อสอบ
	public ActionForward initSuite(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			DynaActionForm dynaForm = (DynaActionForm) form;

			String name = dynaForm.getString("suiteSearch");

			List<ExamSuite> resultList = examSuiteService.getSuiteList(name);
			dynaForm.set("resultList", resultList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward("MA07");
	}

	public ActionForward initAddSuite(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			DynaActionForm dynaForm = (DynaActionForm) form;

			dynaForm.set("id", "");
			dynaForm.set("suiteName", "");
			dynaForm.set("randomType", "N");
			dynaForm.set("scorePass", "");
			dynaForm.set("scoreMax", "");
			dynaForm.set("scorePoint", "1");
			dynaForm.set("examTime", "");
			dynaForm.set("suiteActive", "N");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward("MA08");
	}

	public ActionForward initEditSuite(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			DynaActionForm dynaForm = (DynaActionForm) form;

			ExamSuite entity = examSuiteService.getItem(Integer.parseInt(dynaForm.getString("id")));

			dynaForm.set("suiteName", entity.getName());
			dynaForm.set("randomType", entity.getRandomType());
			dynaForm.set("scorePass", entity.getScorePass() + "");
			dynaForm.set("scoreMax", entity.getScoreMax() + "");
			dynaForm.set("scorePoint", entity.getScorePoint() + "");
			dynaForm.set("examTime", entity.getExamTime() + "");
			dynaForm.set("suiteActive", entity.getActive());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward("MA08");
	}

	public ActionForward saveSuite(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			DynaActionForm dynaForm = (DynaActionForm) form;

			String id = dynaForm.getString("id");

			ExamSuite entity = null;
			if (id != null && !id.equals("")) {
				entity = examSuiteService.getItem(Integer.parseInt(id));
				entity.setUpdateBy(getUserSession(request).getUsername());
				entity.setUpdateDate(DateTimeUtil.getSystemDate());
			} else {
				entity = new ExamSuite();
				entity.setCreateBy(getUserSession(request).getUsername());
				entity.setCreateDate(DateTimeUtil.getSystemDate());
				entity.setTotalQuestion(0);
			}

			entity.setName(dynaForm.getString("suiteName"));
			entity.setRandomType(dynaForm.getString("randomType"));
			entity.setScoreMax(Integer.parseInt(dynaForm.getString("scoreMax")));
			entity.setScorePass(Integer.parseInt(dynaForm.getString("scorePass")));
			entity.setScorePoint(Integer.parseInt(dynaForm.getString("scorePoint")));
			entity.setExamTime(Integer.parseInt(dynaForm.getString("examTime")));
			entity.setActive(dynaForm.getString("suiteActive"));

			examSuiteService.saveOrUpdateItem(entity);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return mappingForward(mapping, request, "mode", "initSuite", "master.htm", "masterForm", null);
	}

	public ActionForward deleteSuite(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			DynaActionForm dynaForm = (DynaActionForm) form;
			examSuiteService.removeItem(Integer.parseInt(dynaForm.getString("id")));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mappingForward(mapping, request, "mode", "initSuite", "master.htm", "masterForm", null);
	}

	public ActionForward initSuiteQuest(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			DynaActionForm dynaForm = (DynaActionForm) form;

			String id = dynaForm.getString("id");

			ExamSuite examSuite = examSuiteService.getItem(Integer.parseInt(id));
			request.setAttribute("PExamSuite", examSuite);

			List<ExamSuiteQuestion> results = examSuiteQuestionService.getSuiteQuestionList(id);
			dynaForm.set("questionList", results);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward("MA09");
	}

	public ActionForward initAddSuiteQuest(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			DynaActionForm dynaForm = (DynaActionForm) form;

			String id = dynaForm.getString("id");

			ExamSuite examSuite = examSuiteService.getItem(Integer.parseInt(id));
			request.setAttribute("PExamSuite", examSuite);

			dynaForm.set("questionAddList", null);

			dynaForm.set("suiteQuestId", "");
			dynaForm.set("questionId", "");

			dynaForm.set("checkIds", null);

			dynaForm.set("comboIndicator", indicatorService.getAll());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward("MA10");
	}

	public ActionForward searchAddSuiteQuest(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			DynaActionForm dynaForm = (DynaActionForm) form;

			String id = dynaForm.getString("id");

			ExamSuite examSuite = examSuiteService.getItem(Integer.parseInt(id));
			request.setAttribute("PExamSuite", examSuite);

			List results = examQuestionService.getExamQuestionList(dynaForm.getString("indicatorIdAdd"), null, null);
			dynaForm.set("questionAddList", results);

			dynaForm.set("questionId", "");
			dynaForm.set("suiteQuestId", "");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward("MA10");
	}

	public ActionForward initView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			DynaActionForm dynaForm = (DynaActionForm) form;

			ExamQuestion question = examQuestionService.getItem(Integer.parseInt(dynaForm.getString("questionId")));

			List<ExamQuestionAnswer> answerList = examQuestionAnswerService.getExamQuestionAnswerList(null, dynaForm.getString("questionId"));
			List<ExamQuestionImage> imageList = examQuestionImageService.getExamQuestionImageList(null, dynaForm.getString("questionId"));

			question.setAnswerList(answerList);
			question.setImageList(imageList);

			request.setAttribute("PExamQuestion", question);

			dynaForm.set("title", question.getTitle());
			dynaForm.set("question", question.getQuestion());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward("MA11");
	}

	public ActionForward initViewManual(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			DynaActionForm dynaForm = (DynaActionForm) form;

			String id = dynaForm.getString("questionId");
			if (id != null && !id.equals("")) {

				// question
				ExamQuestion item = examQuestionService.getItem(Integer.parseInt(id));
				dynaForm.set("templateType", item.getQuestionType()); // 1,2,3

				request.setAttribute("PQuestion", item);
				dynaForm.set("question", item.getQuestion());
				dynaForm.set("title", item.getTitle());

				// image
				List<ExamQuestionImage> imageList = examQuestionImageService.getExamQuestionImageList(null, id);
				dynaForm.set("imageList", imageList);

				request.setAttribute("PImageSize", imageList.size());

				// answer
				List<ExamQuestionAnswer> answerList = examQuestionAnswerService.getExamQuestionAnswerList(null, id);
				dynaForm.set("answerList", answerList);

				request.setAttribute("PAnswerSize", answerList.size());
			}

			resetImageForm(dynaForm);

			String msg = request.getParameter("er");
			if (msg != null && !msg.equals("")) {
				request.setAttribute("msgError", msg);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward("MA12");
	}

	public ActionForward saveSuiteQuest(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			DynaActionForm dynaForm = (DynaActionForm) form;

			String[] checkIds = (String[]) dynaForm.get("checkIds");

			if (checkIds != null && checkIds.length > 0) {

				String examSuiteId = dynaForm.getString("id");

				List<ExamSuiteQuestion> entityList = new ArrayList<ExamSuiteQuestion>();
				for (String id : checkIds) {
					int questionId = Integer.parseInt(id);

					ExamSuiteQuestion entity = new ExamSuiteQuestion();
					entity.setExamSuite(new ExamSuite(Integer.parseInt(examSuiteId)));
					entity.setExamQuestion(new ExamQuestion(questionId));
					entity.setCreateBy(getUserSession(request).getUsername());
					entity.setCreateDate(DateTimeUtil.getSystemDate());
					entityList.add(entity);
				}

				examSuiteQuestionService.saveOrUpdateItems(entityList);

				List suiteList = examSuiteQuestionService.getSuiteQuestionList(examSuiteId);

				ExamSuite examSuite = examSuiteService.getItem(Integer.parseInt(examSuiteId));
				examSuite.setTotalQuestion(suiteList.size());
				examSuiteService.updateItem(examSuite);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return mappingForward(mapping, request, "mode", "initSuiteQuest", "master.htm", "masterForm", null);
	}

	public ActionForward deleteSuiteQuest(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			DynaActionForm dynaForm = (DynaActionForm) form;

			ExamSuiteQuestion entity = examSuiteQuestionService.getItem(Integer.parseInt(dynaForm.getString("suiteQuestId")));
			String examSuiteId = entity.getExamSuite().getId() + "";

			examSuiteQuestionService.removeItem(Integer.parseInt(dynaForm.getString("suiteQuestId")));

			List suiteList = examSuiteQuestionService.getSuiteQuestionList(examSuiteId);

			ExamSuite examSuite = examSuiteService.getItem(Integer.parseInt(examSuiteId));
			examSuite.setTotalQuestion(suiteList.size());
			examSuiteService.updateItem(examSuite);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return mappingForward(mapping, request, "mode", "initSuiteQuest", "master.htm", "masterForm", null);
	}

	public ExamQuestionService getExamQuestionService() {
		return examQuestionService;
	}

	public void setExamQuestionService(ExamQuestionService examQuestionService) {
		this.examQuestionService = examQuestionService;
	}

	public ExamQuestionImageService getExamQuestionImageService() {
		return examQuestionImageService;
	}

	public void setExamQuestionImageService(ExamQuestionImageService examQuestionImageService) {
		this.examQuestionImageService = examQuestionImageService;
	}

	public ExamQuestionAnswerService getExamQuestionAnswerService() {
		return examQuestionAnswerService;
	}

	public void setExamQuestionAnswerService(ExamQuestionAnswerService examQuestionAnswerService) {
		this.examQuestionAnswerService = examQuestionAnswerService;
	}

	public TemplateQuestionService getTemplateQuestionService() {
		return templateQuestionService;
	}

	public void setTemplateQuestionService(TemplateQuestionService templateQuestionService) {
		this.templateQuestionService = templateQuestionService;
	}

	public TemplateImageService getTemplateImageService() {
		return templateImageService;
	}

	public void setTemplateImageService(TemplateImageService templateImageService) {
		this.templateImageService = templateImageService;
	}

	public TemplateAnswerService getTemplateAnswerService() {
		return templateAnswerService;
	}

	public void setTemplateAnswerService(TemplateAnswerService templateAnswerService) {
		this.templateAnswerService = templateAnswerService;
	}

	public IndicatorService getIndicatorService() {
		return indicatorService;
	}

	public void setIndicatorService(IndicatorService indicatorService) {
		this.indicatorService = indicatorService;
	}

	public ConstantService getConstantService() {
		return constantService;
	}

	public void setConstantService(ConstantService constantService) {
		this.constantService = constantService;
	}

	public ExamSuiteService getExamSuiteService() {
		return examSuiteService;
	}

	public void setExamSuiteService(ExamSuiteService examSuiteService) {
		this.examSuiteService = examSuiteService;
	}

	public ExamSuiteQuestionService getExamSuiteQuestionService() {
		return examSuiteQuestionService;
	}

	public void setExamSuiteQuestionService(ExamSuiteQuestionService examSuiteQuestionService) {
		this.examSuiteQuestionService = examSuiteQuestionService;
	}

}
