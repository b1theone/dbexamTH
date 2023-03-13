package com.server.test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.server.gexam.entity.TemplateAnswer;
import com.server.gexam.entity.TemplateImage;
import com.server.gexam.entity.TemplateQuestion;
import com.server.gexam.service.IndicatorService;
import com.server.gexam.service.TemplateAnswerService;
import com.server.gexam.service.TemplateImageService;
import com.server.gexam.service.TemplateQuestionService;
import com.server.gexam.service.UserService;
import com.util.RandomUtil;

import junit.framework.TestCase;

public class TestService extends TestCase {

	private IndicatorService indicatorService = null;
	private TemplateQuestionService templateQuestionService = null;
	private TemplateImageService templateImageService = null;
	private TemplateAnswerService templateAnswerService = null;
	private UserService userService = null;

	private ApplicationContext ctx = null;
	SessionFactory sessionFactory = null;

	public TestService(String name) {
		super(name);
		String[] paths = { "/com/server/gexam/conf/application-context.xml" };
		ctx = new ClassPathXmlApplicationContext(paths);
	}

	protected void setUp() throws Exception {
		super.setUp();
		sessionFactory = (SessionFactory) ctx.getBean("sessionFactory");
		Session s = sessionFactory.openSession();
		TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(s));

		indicatorService = (IndicatorService) ctx.getBean("indicatorService");
		templateQuestionService = (TemplateQuestionService) ctx.getBean("templateQuestionService");
		templateImageService = (TemplateImageService) ctx.getBean("templateImageService");
		templateAnswerService = (TemplateAnswerService) ctx.getBean("templateAnswerService");
		userService = (UserService) ctx.getBean("userService");

	}

	protected void tearDown() throws Exception {
		super.tearDown();
		SessionHolder holder = (SessionHolder) TransactionSynchronizationManager.getResource(sessionFactory);
		Session s = holder.getSession();
		s.flush();
		TransactionSynchronizationManager.unbindResource(sessionFactory);
		SessionFactoryUtils.closeSession(s);

		indicatorService = null;
		templateQuestionService = null;
		templateImageService = null;
		templateAnswerService = null;
		userService = null;

	}

	public void test() throws Exception {
		int questionId = 7;
		System.out.print(userService.getAllItems().size());
		TemplateQuestion quest = templateQuestionService.getItem(questionId);

		// à¸•à¸±à¸§à¹�à¸›à¸£à¸Šà¸·à¹ˆà¸­à¸ªà¸³à¸«à¸£à¸±à¸šà¸„à¸³à¸•à¸­à¸š
		String[] names = null;
		if (quest.getName4() != null && !quest.getName4().equals("")) {
			names = new String[] { quest.getName1(), quest.getName2(), quest.getName3(), quest.getName4() };
		} else if (quest.getName3() != null && !quest.getName3().equals("")) {
			names = new String[] { quest.getName1(), quest.getName2(), quest.getName3() };
		} else if (quest.getName2() != null && !quest.getName2().equals("")) {
			names = new String[] { quest.getName1(), quest.getName2() };
		}

		List<TemplateImage> images = templateImageService.getTemplateImageList(questionId + "", null);

		// 1. create question
		HashMap<Integer, TemplateImage> qMap = new HashMap<Integer, TemplateImage>();
		int rQ = 0;
		int last = 0;
		for (TemplateImage img : images) {
			qMap.put(rQ++, img);
		}
		last = rQ;

		// 2. random question image
		int[] quests = ThreadLocalRandom.current().ints(0, last).distinct().limit(images.size()).toArray();
		int[] amounts = new int[images.size()];
		if (quest.getParameter2() > 0)
			amounts = ThreadLocalRandom.current().ints(quest.getParameter1(), quest.getParameter2() + 1).distinct().limit(images.size()).toArray();

		int[] rates = new int[images.size()];
		if (quest.getRateType().equals("Y")) {
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
			// à¹€à¸‹à¸•à¸„à¹ˆà¸²à¸—à¸µà¹ˆà¹€à¸«à¸¡à¸·à¸­à¸™à¸„à¹ˆà¸²à¹�à¸£à¸�
			rates[images.size() - 1] = rates[1];
		}

		// à¹€à¸£à¸µà¸¢à¸‡à¸¥à¸³à¸”à¸±à¸šà¸•à¸±à¸§à¹€à¸¥à¸·à¸­à¸�à¸„à¸³à¸–à¸²à¸¡
		TreeMap<Integer, TemplateImage> seqQMap = new TreeMap<Integer, TemplateImage>();

		int a = 0;
		if (quest.getRandomType().equals("Y")) { // à¸ªà¸¸à¹ˆà¸¡
			for (int j : quests) {
				if (qMap.containsKey(j)) {
					TemplateImage img = qMap.get(j);

					if (quest.getRateType().equals("Y")) { // à¸„à¸¹à¸“à¸­à¸±à¸•à¸£à¸²à¸ªà¹ˆà¸§à¸™
						img.setItem(amounts[a]);
						img.setAmount(amounts[a] * rates[a]);
					} else {
						img.setAmount(amounts[a]);
					}

					if (!quest.getConditionType().equals("3") && !quest.getConditionType().equals("4")) {
						if (quest.getRateType().equals("Y")) {
							System.out.println(img.getImageName() + " " + img.getItem() + " " + img.getUnit() + " " + img.getUnitName() + " " + img.getAmount());
						} else {
							System.out.println(img.getImageName() + " " + img.getUnitName() + " " + amounts[a] + " " + img.getUnit());
						}
					} else {
						System.out.println(img.getImageName() + " " + amounts[a]);
					}
					seqQMap.put(amounts[a], img);

					a++;
				}
			}
		} else { // à¹„à¸¡à¹ˆà¸ªà¸¸à¹ˆà¸¡
			int amount1 = 0;
			int amount2 = 0;
			for (TemplateImage img : images) {

				int amount = amounts[a];

				if (quest.getRateType().equals("Y")) { // à¸„à¸¹à¸“à¸­à¸±à¸•à¸£à¸²à¸ªà¹ˆà¸§à¸™
					img.setItem(amount);
					img.setAmount(amount * rates[a]);
				} else {
					if (img.getOptionType().equals("1")) { // à¸ˆà¸³à¸™à¸§à¸™à¹€à¸•à¹‡à¸¡
						img.setAmount(17000); // default
						amount1 = 17000;

						int[] numbs = ThreadLocalRandom.current().ints(img.getParameter1(), img.getParameter2() + 1).distinct().limit(10).toArray();
						for (int ra : numbs) {
							if (ra % 100 == 0) {
								img.setAmount(ra);
								amount1 = ra;
								break;
							}
						}

						amount = img.getAmount();
					} else if (img.getOptionType().equals("2")) { // à¸«à¸±à¸�à¸¥à¸š
						img.setAmount(8000); // default
						amount2 = 8000;

						int[] numbs = ThreadLocalRandom.current().ints(img.getParameter1(), img.getParameter2() + 1).distinct().limit(10).toArray();
						for (int ra : numbs) {
							if (ra % 100 == 0) {
								img.setAmount(ra);
								amount2 = ra;
								break;
							}
						}

						amount = img.getAmount();
					} else if (img.getOptionType().equals("3")) { // à¹�à¸šà¹ˆà¸‡à¸ªà¹ˆà¸§à¸™
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
					if (quest.getRateType().equals("Y")) {
						System.out.println(img.getImageName() + " " + img.getItem() + " " + img.getUnit() + " " + img.getUnitName() + " " + img.getAmount());
					} else {
						System.out.println(img.getImageName() + " " + img.getUnitName() + " " + amount + " " + img.getUnit());
					}
				} else {
					System.out.println(img.getImageName() + " " + amount);
				}
				seqQMap.put(amount, img);

				a++;
			}
		}

		// 3. à¸ªà¸¸à¹ˆà¸¡à¸„à¸³à¸•à¸­à¸š
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

		if (rA < 3) { // à¸„à¸³à¸•à¸­à¸šà¹„à¸¡à¹ˆà¸„à¸£à¸š 4
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

		// à¸„à¸³à¸•à¸­à¸šà¸—à¸µà¹ˆà¸–à¸¹à¸�à¸•à¹‰à¸­à¸‡
		TemplateAnswer ansTrue = null;
		// à¸„à¸³à¸•à¸­à¸šà¸—à¸µà¹ˆà¸œà¸´à¸”
		List<TemplateAnswer> ansFalseList = new ArrayList<TemplateAnswer>();

		int[] randomNums = ThreadLocalRandom.current().ints(0, 4).distinct().limit(4).toArray();
		for (int i = 0; i < randomNums.length; i++) {
			if (aMap.containsKey(randomNums[i])) {
				TemplateAnswer ans = aMap.get(randomNums[i]);

				if (i == 0)
					ansTrue = ans;
				else
					ansFalseList.add(ans);
			}
		}

		List<TemplateImage> imgList = new ArrayList<TemplateImage>(seqQMap.values());

		if (!quest.getConditionType().equals("3") && !quest.getConditionType().equals("4")) {
			System.out.println(createAnswer(quest, ansTrue, seqQMap, imgList, true)[0]);

			for (TemplateAnswer ansFalse : ansFalseList) {
				System.out.println(createAnswer(quest, ansFalse, seqQMap, imgList, false)[0]);
			}
		} else {
			// à¹ƒà¸Šà¹‰à¸ªà¸³à¸«à¸£à¸±à¸šà¹€à¸£à¸µà¸¢à¸‡à¸¥à¸³à¸”à¸±à¸š
			TreeMap<Integer, String> rsMap = new TreeMap<Integer, String>();
			Object[] objT = createAnswer(quest, ansTrue, seqQMap, imgList, true);
			rsMap.put(Integer.parseInt(String.valueOf(objT[1])), String.valueOf(objT[0]));

			for (TemplateAnswer ansFalse : ansFalseList) {
				Object[] objF = createAnswer(quest, ansFalse, seqQMap, imgList, true);
				rsMap.put(Integer.parseInt(String.valueOf(objF[1])), String.valueOf(objF[0]));
			}

			for (Integer key : rsMap.keySet()) {
				System.out.println(rsMap.get(key));
			}
		}

	}

	public static Object[] createAnswer(TemplateQuestion quest, TemplateAnswer ansTrue, TreeMap<Integer, TemplateImage> seqQMap, List<TemplateImage> imgList, boolean isAnswer) throws Exception {
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
		} else if (imgList.size() < 4) {
			numMin = "01";
			numMax = "12";
		}

		if (ansTrue.getConditionType().equals("01")) {
			// à¸¡à¸²à¸�à¸ªà¸¸à¸”
			if (isAnswer) {
				answer = ansTrue.getAnswer().replace("{param1}", seqQMap.get(Collections.max(seqQMap.keySet())).getImageName());
			} else {
				int seq = Integer.parseInt(RandomUtil.randomNumber(numMin, 1));
				answer = ansTrue.getAnswer().replace("{param1}", imgList.get(seq).getImageName());
			}
		} else if (ansTrue.getConditionType().equals("02")) {
			// à¸™à¹‰à¸­à¸¢à¸ªà¸¸à¸”
			if (isAnswer) {
				answer = ansTrue.getAnswer().replace("{param1}", seqQMap.get(Collections.min(seqQMap.keySet())).getImageName());
			} else {
				int seq = Integer.parseInt(RandomUtil.randomNumber(numMax, 1));
				answer = ansTrue.getAnswer().replace("{param1}", imgList.get(seq).getImageName());
			}
		} else if (ansTrue.getConditionType().equals("03")) {
			// à¸¡à¸²à¸�à¸�à¸§à¹ˆà¸²
			if (isAnswer) {
				int seq = Integer.parseInt(RandomUtil.randomNumber(numMax, 1));
				answer = (ansTrue.getAnswer().replace("{param1}", imgList.get(seq).getImageName())).replace("{param2}", imgList.get(seq - 1).getImageName());
			} else {
				int seq = Integer.parseInt(RandomUtil.randomNumber(numMin, 1));
				answer = (ansTrue.getAnswer().replace("{param1}", imgList.get(seq).getImageName())).replace("{param2}", imgList.get(seq + 1).getImageName());
			}
		} else if (ansTrue.getConditionType().equals("04")) {
			// à¸™à¹‰à¸­à¸¢à¸�à¸§à¹ˆà¸²
			if (isAnswer) {
				int seq = Integer.parseInt(RandomUtil.randomNumber(numMin, 1));
				answer = (ansTrue.getAnswer().replace("{param1}", imgList.get(seq).getImageName())).replace("{param2}", imgList.get(seq + 1).getImageName());
			} else {
				int seq = Integer.parseInt(RandomUtil.randomNumber(numMax, 1));
				answer = (ansTrue.getAnswer().replace("{param1}", imgList.get(seq).getImageName())).replace("{param2}", imgList.get(seq - 1).getImageName());
			}
		} else if (ansTrue.getConditionType().equals("05")) {
			// à¸™à¹‰à¸­à¸¢à¹€à¸›à¹‡à¸™à¸­à¸±à¸™à¸”à¸±à¸šà¸—à¸µà¹ˆ
			if (isAnswer) {
				int ord = Integer.parseInt(RandomUtil.randomNumber(numMin, 1));
				answer = (ansTrue.getAnswer().replace("{param1}", imgList.get(ord).getImageName())).replace("{order}", (ord + 1) + "");
			} else {
				int ord = Integer.parseInt(RandomUtil.randomNumber(numMax, 1));
				answer = (ansTrue.getAnswer().replace("{param1}", imgList.get(ord).getImageName())).replace("{order}", (4 - ord) + "");
			}
		} else if (ansTrue.getConditionType().equals("06")) {
			// à¸¡à¸²à¸�à¹€à¸›à¹‡à¸™à¸­à¸±à¸™à¸”à¸±à¸šà¸—à¸µà¹ˆ
			if (isAnswer) {
				int ord = Integer.parseInt(RandomUtil.randomNumber(numMax, 1));
				answer = (ansTrue.getAnswer().replace("{param1}", imgList.get(ord).getImageName())).replace("{order}", (4 - ord) + "");
			} else {
				int ord = Integer.parseInt(RandomUtil.randomNumber(numMin, 1));
				answer = (ansTrue.getAnswer().replace("{param1}", imgList.get(ord).getImageName())).replace("{order}", (ord + 1) + "");
			}
		} else if (ansTrue.getConditionType().equals("07")) {
			// à¸œà¸¥à¸£à¸§à¸¡à¹€à¸—à¹ˆà¸²à¸�à¸±à¸š
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
			// à¸œà¸¥à¸„à¸¹à¸“à¹€à¸›à¸£à¸µà¸¢à¸šà¹€à¸—à¸µà¸¢à¸š
			if (isAnswer) {
				boolean check = true;
				while (check) {
					int seq = Integer.parseInt(RandomUtil.randomNumber(numMin, 1));
					int num1 = Integer.parseInt(RandomUtil.randomNumber("12", 1));
					int num2 = Integer.parseInt(RandomUtil.randomNumber("12", 1));
					TemplateImage img1 = imgList.get(seq);
					TemplateImage img2 = imgList.get(seq + 1);
					int total = img1.getAmount() * num1 + img2.getAmount() * num2;

					if (quest.getDefaultAmount() > 0) {
						if (total < quest.getDefaultAmount()) {
							check = false;
							answer = (((((ansTrue.getAnswer().replace("{param1}", img1.getImageName())).replace("{amount1}", num1 + "")).replace("{param2}", img2.getImageName())).replace("{amount2}", num2 + "")).replace("{unit1}", img1.getUnit())).replace("{unit2}", img2.getUnit());
						}
					} else {
						check = false;
						answer = (((((ansTrue.getAnswer().replace("{param1}", img1.getImageName())).replace("{amount1}", num1 + "")).replace("{param2}", img2.getImageName())).replace("{amount2}", num2 + "")).replace("{unit1}", img1.getUnit())).replace("{unit2}", img2.getUnit());
					}

					data = total;
				}
			} else {
				boolean check = true;
				while (check) {
					int seq = Integer.parseInt(RandomUtil.randomNumber(numMin, 1));
					int num1 = Integer.parseInt(RandomUtil.randomNumber("12", 1));
					int num2 = Integer.parseInt(RandomUtil.randomNumber("12", 1));
					TemplateImage img1 = imgList.get(seq);
					TemplateImage img2 = imgList.get(seq + 1);
					int total = img1.getAmount() * num1 + img2.getAmount() * num2;

					if (quest.getDefaultAmount() > 0) {
						if (total > quest.getDefaultAmount()) {
							check = false;
							answer = (((((ansTrue.getAnswer().replace("{param1}", img1.getImageName())).replace("{amount1}", num1 + "")).replace("{param2}", img2.getImageName())).replace("{amount2}", num2 + "")).replace("{unit1}", img1.getUnit())).replace("{unit2}", img2.getUnit());
						}
					} else {
						check = false;
						answer = (((((ansTrue.getAnswer().replace("{param1}", img1.getImageName())).replace("{amount1}", num1 + "")).replace("{param2}", img2.getImageName())).replace("{amount2}", num2 + "")).replace("{unit1}", img1.getUnit())).replace("{unit2}", img2.getUnit());
					}

					data = total;
				}
			}
		} else if (ansTrue.getConditionType().equals("09")) {
			// à¸„à¸‡à¹€à¸«à¸¥à¸·à¸­
			if (quest.getCalculateType().equals("1")) {
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
			} else {
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
			// à¸œà¸¥à¸„à¸¹à¸“à¸£à¸§à¸¡à¸�à¸±à¸™à¹€à¸—à¹ˆà¸²à¸�à¸±à¸š
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
			// à¸­à¸±à¸•à¸£à¸²à¸ªà¹ˆà¸§à¸™à¹€à¸›à¸£à¸µà¸¢à¸šà¹€à¸—à¸µà¸¢à¸š
			int seq1 = 0;
			TemplateImage img1 = imgList.get(seq1);
			int num1 = 1;
			int amount1 = (img1.getAmount() / img1.getItem()) * num1;

			boolean check = true;
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
					}
				} else {
					if (amount1 != amount2) {
						check = false;
						answer = (((((ansTrue.getAnswer().replace("{param1}", img1.getImageName())).replace("{amount1}", num1 + "")).replace("{unit1}", img1.getUnit())).replace("{param2}", img2.getImageName())).replace("{amount2}", num2 + "")).replace("{unit2}", img2.getUnit());
					}
				}

			}
		} else if (ansTrue.getConditionType().equals("12")) {
			// à¸­à¸±à¸•à¸£à¸²à¸ªà¹ˆà¸§à¸™à¸ªà¹ˆà¸§à¸™à¸•à¹ˆà¸²à¸‡
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
			// à¸™à¹‰à¸­à¸¢à¹„à¸›à¸¡à¸²à¸�
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
