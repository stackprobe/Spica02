package rafalerevive.poi.tests;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFCreationHelper;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import charlotte.tools.FileTools;

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

			// 画像ファイル貼り付け
			{
				int rowidx = 3;
				int colidx = 4;
				double scale = 10.0;

				byte[] png = FileTools.readAllBytes("C:/var/mat/20181102_StaGeoデモ/20181102_デモ画像/20181102180024.png");

				int picIndex = wb.addPicture(png, HSSFWorkbook.PICTURE_TYPE_PNG);

				HSSFCreationHelper helper = (HSSFCreationHelper)wb.getCreationHelper();
				HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
				HSSFClientAnchor anchor = helper.createClientAnchor();
				anchor.setRow1(rowidx);
				anchor.setCol1(colidx);

				HSSFPicture picture = patriarch.createPicture(anchor, picIndex);
				picture.resize(scale);
			}

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
			XSSFWorkbook wb = new XSSFWorkbook();

			wb.createSheet();
			XSSFSheet sheet = wb.getSheetAt(0);

			// 画像ファイル貼り付け
			{
				int colPix = 5;
				int rowPix = 10;
				int colPix2 = 50;
				int rowPix2 = 15;
				int rowidx = 3;
				int colidx = 4;
				int rowidx2 = 20;
				int colidx2 = 10;

				// rowidx行colidx列のセルの左上から右にcolPix下にrowPixの位置を画像の左上
				// rowidx2行colidx2列のセルの左上から右にcolPix2下にrowPix2の位置を画像の右下
				// にして画像を貼り付ける。

				byte[] png = FileTools.readAllBytes("C:/var/mat/20181102_StaGeoデモ/20181102_デモ画像/20181102180024.png");

				int picIndex = wb.addPicture(png, XSSFWorkbook.PICTURE_TYPE_PNG);

				XSSFDrawing drawing = sheet.createDrawingPatriarch();
				XSSFClientAnchor anchor = drawing.createAnchor(
						org.apache.poi.util.Units.EMU_PER_PIXEL * colPix,
						org.apache.poi.util.Units.EMU_PER_PIXEL * rowPix,
						org.apache.poi.util.Units.EMU_PER_PIXEL * colPix2,
						org.apache.poi.util.Units.EMU_PER_PIXEL * rowPix2,
						colidx,
						rowidx,
						colidx2,
						rowidx2
						);

				//anchor.setAnchorType(2); // ???

				XSSFPicture picture = drawing.createPicture(anchor, picIndex);
			}

			try(FileOutputStream writer = new FileOutputStream("C:/temp/Test0001_03.xlsx")) {
				wb.write(writer);
			}
			try(FileInputStream reader = new FileInputStream("C:/temp/Test0001_03.xlsx")) {
				wb = new XSSFWorkbook(reader);
			}
			try(FileOutputStream writer = new FileOutputStream("C:/temp/Test0001_04.xlsx")) {
				wb.write(writer);
			}
		}

		test01_a("C:/temp/Test0001_02.xls");
		test01_a("C:/temp/Test0001_04.xlsx");
	}

	private static void test01_a(String excelFile) throws Exception {
		Workbook wb;

		try(FileInputStream reader = new FileInputStream(excelFile)) {
			wb = WorkbookFactory.create(reader);
		}

		// todo

		try(FileOutputStream writer = new FileOutputStream(excelFile)) {
			wb.write(writer);
		}
	}
}
