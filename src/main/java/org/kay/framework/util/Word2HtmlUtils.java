package org.kay.framework.util;

import org.apache.log4j.Logger;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public abstract class Word2HtmlUtils {

	private static final Logger logger = Logger.getLogger(Word2HtmlUtils.class);

	public static final int WORD_HTML = 8;
	public static final int WORD_TXT = 7;
	public static final int EXCEL_HTML = 44;

	/**
	 * @Usage
	 * Word2HtmlUtil.convertWord2Html("e:\\abc.docx", "f:\\xxx.html");
	 */
	public static void convertWord2Html(String docfile, String htmlfile) {

		try {
			// Initialize COM Thread
			ComThread.InitSTA();

			// Create Server object
			ActiveXComponent word = new ActiveXComponent("Word.Application");

			// 设置word不可见
			word.setProperty("Visible", new Variant(false));

			// 获得documents对象
			Dispatch docs = (Dispatch) word.getProperty("Documents").toDispatch();

			// 打开Word文件
			Dispatch doc = openWord(docfile, docs);

			// 将Word文件另存为Html文件
			saveWordAsHtml(docfile, doc);

			Variant f = new Variant(false);

			Dispatch.call(doc, "Close", f);

		} catch (Exception e) {
			logger.warn("An error occurred at conversion: " + e.toString());
		} finally {
			ComThread.Release();
		}
	}

	private static Dispatch openWord(String docfile, Dispatch docs) {
		Object[] oArg = new Object[] { docfile, new Variant(false), new Variant(true) };
		Dispatch doc = Dispatch.invoke(docs, "Open", Dispatch.Method, oArg, new int[1]).toDispatch();
		return doc;
	}

	private static void saveWordAsHtml(String docfile, Dispatch doc) {
		Object[] oArg = new Object[] { docfile, new Variant(false), new Variant(true) };
		Dispatch.invoke(doc, "SaveAs", Dispatch.Method, oArg, new int[1]);
	}

}
