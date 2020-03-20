package deneb.requirelib.poi.tests;

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

			// 画像ファイル貼り付け // orig: // \u753b\u50cf\u30d5\u30a1\u30a4\u30eb\u8cbc\u308a\u4ed8\u3051
			{
				int rowidx = 3;
				int colidx = 4;
				double scale = 10.0;

				byte[] png = FileTools.readAllBytes("C:/Erika/wb/20181102_StaGeoデモ/20181102_デモ画像/20181102180024.png"); // orig: byte[] png = FileTools.readAllBytes("C:/Erika/wb/20181102_StaGeo\u30c7\u30e2/20181102_\u30c7\u30e2\u753b\u50cf/20181102180024.png");

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

			// 画像ファイル貼り付け // orig: // \u753b\u50cf\u30d5\u30a1\u30a4\u30eb\u8cbc\u308a\u4ed8\u3051
			{
				int colPix = 5;
				int rowPix = 10;
				int colPix2 = 50;
				int rowPix2 = 15;
				int rowidx = 3;
				int colidx = 4;
				int rowidx2 = 20;
				int colidx2 = 10;

				// rowidx 行 colidx 列のセルの左上から右に colPix 下に rowPix の位置を画像の左上 // orig: // rowidx \u884c colidx \u5217\u306e\u30bb\u30eb\u306e\u5de6\u4e0a\u304b\u3089\u53f3\u306b colPix \u4e0b\u306b rowPix \u306e\u4f4d\u7f6e\u3092\u753b\u50cf\u306e\u5de6\u4e0a
				// rowidx2 行 colidx2 列のセルの左上から右に colPix2 下に rowPix2 の位置を画像の右下 // orig: // rowidx2 \u884c colidx2 \u5217\u306e\u30bb\u30eb\u306e\u5de6\u4e0a\u304b\u3089\u53f3\u306b colPix2 \u4e0b\u306b rowPix2 \u306e\u4f4d\u7f6e\u3092\u753b\u50cf\u306e\u53f3\u4e0b
				// にして画像を貼り付ける。 // orig: // \u306b\u3057\u3066\u753b\u50cf\u3092\u8cbc\u308a\u4ed8\u3051\u308b\u3002

				byte[] png = FileTools.readAllBytes("C:/Erika/wb/20181102_StaGeoデモ/20181102_デモ画像/20181102180024.png"); // orig: byte[] png = FileTools.readAllBytes("C:/Erika/wb/20181102_StaGeo\u30c7\u30e2/20181102_\u30c7\u30e2\u753b\u50cf/20181102180024.png");

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
