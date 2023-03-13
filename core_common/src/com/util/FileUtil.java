package com.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class FileUtil {

	public static final String FILE_SEPARATOR = System.getProperty("file.separator");
	public static final int BUFFER_SIZE = 1024;

	public static byte[] zipFile2Byte(String directory, String[] files) throws IOException {
		ByteArrayOutputStream baos = null;
		ZipOutputStream zos = null;
		try {
			baos = new ByteArrayOutputStream();
			zos = new ZipOutputStream(baos);

			byte bytes[] = new byte[BUFFER_SIZE];

			for (String fileName : files) {
				FileInputStream fis = new FileInputStream(directory + FILE_SEPARATOR + fileName);
				BufferedInputStream bis = new BufferedInputStream(fis);

				zos.putNextEntry(new ZipEntry(fileName));

				int bytesRead;
				while ((bytesRead = bis.read(bytes)) != -1) {
					zos.write(bytes, 0, bytesRead);
				}

				zos.closeEntry();
				bis.close();
				fis.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (zos != null) {
				zos.flush();
				zos.close();
			}

			if (baos != null) {
				baos.flush();
				baos.close();
			}
		}

		return baos.toByteArray();
	}

	public static boolean delFile(String path, String fileDel) {
		try {
			boolean result = (new File(path + fileDel)).delete();
			return result;
		} catch (Exception e) {
			return false;
		}

	}

	public static int getLineNumber(File file) throws IOException {
		int totalLine = 0;
		LineNumberReader lnr = null;
		try {
			lnr = new LineNumberReader(new FileReader(file));
			lnr.skip(Long.MAX_VALUE);
			totalLine = lnr.getLineNumber();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (lnr != null) {
				lnr.close();
			}
		}

		return totalLine;
	}

	public static ByteArrayOutputStream zipFile2Stream(String directory, String[] files) throws IOException {
		ByteArrayOutputStream baos = null;
		ZipOutputStream zos = null;
		try {
			baos = new ByteArrayOutputStream();
			zos = new ZipOutputStream(baos);

			byte bytes[] = new byte[BUFFER_SIZE];

			for (String fileName : files) {
				FileInputStream fis = new FileInputStream(directory + FILE_SEPARATOR + fileName);
				BufferedInputStream bis = new BufferedInputStream(fis);

				zos.putNextEntry(new ZipEntry(fileName));

				int bytesRead;
				while ((bytesRead = bis.read(bytes)) != -1) {
					zos.write(bytes, 0, bytesRead);
				}

				zos.closeEntry();
				bis.close();
				fis.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (zos != null) {
				zos.flush();
				zos.close();
			}

			if (baos != null) {
				baos.flush();
				baos.close();
			}
		}

		return baos;
	}
		
	public static String unzip(final ZipInputStream zipInputStream, final String unzipLocation) throws IOException {
		String fullPath = unzipLocation;		

		if (!(Files.exists(Paths.get(unzipLocation)))) {
			Files.createDirectories(Paths.get(unzipLocation));
		}
		
		try {
			
		//try(ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipFilePath)))
		// try(ZipInputStream zipInputStream = new ZipInputStream(new
		// FileInputStream(zipFilePath)))
		if (zipInputStream != null) {
			ZipEntry entry = zipInputStream.getNextEntry();
//			ZipEntry entry;
			while (entry != null) {
				
				String namefile;
				if(entry.getName().lastIndexOf("/")!=-1){
					 namefile = entry.getName().substring(entry.getName().lastIndexOf("/")+1);	
				}else{
					 namefile = entry.getName();
				}			
				//check DBF
				if(namefile.substring(namefile.lastIndexOf("."), namefile.length()).equalsIgnoreCase(".DBF")||namefile.substring(namefile.lastIndexOf("."), namefile.length()).equalsIgnoreCase(".dbf")){					
					File newFile = new File(unzipLocation + File.separator + namefile);
					System.out.println("Unzipping to=> "+newFile.getAbsolutePath());
					
						Path filePath = Paths.get(unzipLocation, namefile);
						if (entry.getName().indexOf("MACOSX") == -1) {
							if (!entry.isDirectory()) {
								unzipFiles(zipInputStream, filePath);
							} else {
								Files.createDirectories(filePath);
								fullPath = filePath.toString();
							}
						}
//					zipInputStream.closeEntry();
//					entry = zipInputStream.getNextEntry();
				}else{					
					System.out.println("Unzipping to can't not==> "+namefile);

				}
				zipInputStream.closeEntry();
				entry = zipInputStream.getNextEntry();
			}			
		}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return fullPath;
	}

	public static void unzipFiles(final ZipInputStream zipInputStream, final Path unzipFilePath) throws IOException {

		try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(unzipFilePath.toAbsolutePath().toString()))) {
			byte[] bytesIn = new byte[1024];
			int read = 0;
			while ((read = zipInputStream.read(bytesIn)) != -1) {
				bos.write(bytesIn, 0, read);
			}
		}

	}
}
