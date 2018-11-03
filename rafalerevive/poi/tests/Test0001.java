package rafalerevive.poi.tests;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Test0001 {
	public static void main(String[] args) {
		try {
			test01();

			System.out.println("OK!");
		}
		catch(Throwable e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

	private static void test01() throws Exception {
		{
			HSSFWorkbook  wb = new HSSFWorkbook();

			wb.createSheet();
			HSSFSheet sheet = wb.getSheetAt(0);

			// todo

			try(FileOutputStream writer = new FileOutputStream("C:/temp/Test0001_01.xls")) {
				wb.write(writer);
			}
			try(FileInputStream reader = new FileInputStream("C:/temp/Test0001_01.xls")) {
				wb = new HSSFWorkbook(new POIFSFileSystem(reader));
			}
			try(FileOutputStream writer = new FileOutputStream("C:/temp/Test0001_02.xls")) {
				wb.write(writer);
			}
		}

		{
			XSSFWorkbook wb = new XSSFWorkbook(); // TODO java.lang.NoClassDefFoundError: org/apache/commons/compress/archivers/zip/ZipFile

			wb.createSheet();
			XSSFSheet sheet = wb.getSheetAt(0);

			// todo

			try(FileOutputStream writer = new FileOutputStream("C:/temp/Test0001_01.xlsx")) {
				wb.write(writer);
			}
			try(FileInputStream reader = new FileInputStream("C:/temp/Test0001_01.xlsx")) {
				wb = new XSSFWorkbook(reader);
			}
			try(FileOutputStream writer = new FileOutputStream("C:/temp/Test0001_02.xlsx")) {
				wb.write(writer);
			}
		}
	}
}
