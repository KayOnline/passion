package org.kay.framework.util.print2flash;

import org.apache.log4j.Logger;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Variant;

/**
 * @DESCRIPTION
 * Support types:
 * doc, docx, ppt, pptx, xls, xlsx, rtf, htm, html, pdf, jpg,
 * jpeg, tif, tiff, bmp, gif, png, txt, odt, odg, odp, odf
 * @author Kay
 */
public abstract class Print2FlashUtils {

	private static final Logger logger = Logger.getLogger(Print2FlashUtils.class);
	
	/**
	 * @Description
	 * Method converts all specified files in a directory to Flash.
	 * 
	 * @Usage
	 * String SourceDirName = "C:\\Users\\Administrator\\Desktop\\word";
	 * String SourceFileMask = "*.doc";
	 * String OutputDirName = "C:\\Users\\Administrator\\Desktop\\swf";
	 * Boolean IncludeSubDirs = Boolean.FALSE;
	 * Print2FlashUtil.convertDir(SourceDirName, SourceFileMask, OutputDirName, IncludeSubDirs);
	 *
	 * @param SourceDirName 
	 *		The full path and name of the directory where all the document to convert reside.
	 * @param SourceFileMask 
	 *		The mask for the files to convert, e.g. "*.doc". If this argument is not specified, 
	 *		all files are attempted to convert
	 * @param OutputDirName 
	 * 		The full path and name of the output directory to which resulting Flash files are written. 
	 * 		If this parameter is omitted, the Flash files are written to the same directory as SourceDirName.
	 * @param IncludeSubDirs 
	 * 		Boolean indicator specifying whether to look for files to convert in all subdirectories of 
	 * 		the SourceDirName directory in addition to the files located directly in SourceDirName directory. 
	 * 		The default is false.
	 */
	public static void convertDir(String SourceDirName, String SourceFileMask, String OutputDirName, Boolean IncludeSubDirs) {
		try {
			// Initialize COM Thread
			ComThread.InitSTA();

			// Construct ActiveX Component instance.
			ActiveXComponent p2f = new ActiveXComponent("Print2Flash3.Server");
			
			// Setup interface and protection options.
			setInterfaceAndProtectionOptions(p2f);
			
			// Converts all specified files in a directory to Flash.
			p2f.invoke("ConvertDir", new Variant(SourceDirName), new Variant(SourceFileMask), new Variant(OutputDirName), new Variant(IncludeSubDirs));

		} catch (Exception e) {
			logger.error("An error occurred at conversion: " + e.toString(), e);
		} finally {
			ComThread.Release();
		}
	}

	/**
	 * @Description
	 * Method converts a single file to Flash.
	 * 
	 * @Usage
	 * String sourceFileName = "C:\\Users\\Administrator\\Desktop\\word\\test.doc";
	 * String outputFileName = "C:\\Users\\Administrator\\Desktop\\swf\\test.swf";
	 * Print2FlashUtil.convertFile(sourceFileName, outputFileName);
	 *
	 * @param sourceFileName 
	 * 		The full path and name of the source document file.
	 * @param outputFileName  	
	 * 		The full path and name of the output Flash file. If this parameter is omitted, 
	 * 		the file is named after the original document with .swf extension appended to 
	 * 		the original document name and is put into the same folder as the original file.
	 */
	public static void convertFile(String sourceFileName, String outputFileName) {
		try {
			// Initialize COM Thread
			ComThread.InitSTA();
			
			// Construct ActiveX Component instance
			ActiveXComponent p2f = new ActiveXComponent("Print2Flash3.Server");
			
			// Setup interface and protection options
			setInterfaceAndProtectionOptions(p2f);
			
			// Convert document
			p2f.invoke("ConvertFile", new Variant(sourceFileName), new Variant(outputFileName));

		} catch (Exception e) {
			logger.error("An error occurred at conversion: " + e.toString(), e);
		} finally {
			ComThread.Release();
		}
	}

	private static void setInterfaceAndProtectionOptions(ActiveXComponent p2f) {
		
		ActiveXComponent defProfile = new ActiveXComponent(p2f.getProperty("DefaultProfile").toDispatch());
		
		// Set InterfaceOptions
        int interfaceOptions = P2FConst.INTLOGO | P2FConst.INTZOOMSLIDER | P2FConst.INTPREVPAGE 
        		             | P2FConst.INTGOTOPAGE | P2FConst.INTNEXTPAGE | P2FConst.IMGFITWIDTH 
        		             | P2FConst.INTFULLSCREEN;
        defProfile.setProperty("InterfaceOptions", interfaceOptions);
        
        // Set ProtectionOptions
        int protectionOptions = P2FConst.PROTDISPRINT | P2FConst.PROTENAPI;
		defProfile.setProperty("ProtectionOptions", protectionOptions);
		
	}

}
