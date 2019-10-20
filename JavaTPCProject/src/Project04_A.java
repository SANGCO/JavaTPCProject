import java.io.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class Project04_A {
	public static void main(String[] args) {
     // iText API
	String[] title=new String[] {"����", "����", "���ǻ�", "�̹���URL"};
	String[][] rows=new String[][] {
		{"������Ģ�� ����", "��ó�� ���θ�", "�س���", "https://bookthumb-phinf.pstatic.net/cover/100/365/10036542.jpg"},
		{"Java�� ����", "���ü�", "��������", "https://bookthumb-phinf.pstatic.net/cover/100/365/10036542.jpg"},
		{"������ ���α׷���", "â����", "��������", "https://bookthumb-phinf.pstatic.net/cover/100/365/10036542.jpg"},
	};       
	Document doc=new Document(PageSize.A4);
	try {
		PdfWriter.getInstance(doc, new FileOutputStream(new File("book.pdf")));
		doc.open();
		//�ѱ� ��Ʈ
		BaseFont bf=BaseFont.createFont("NANUMMYEONGJO.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		Font fontTitle=new Font(bf, 12);
		Font fontRows=new Font(bf, 10);
		
		PdfPTable table=new PdfPTable(title.length);
		table.setWidthPercentage(100);
		
		float[] colwidth=new float[] {20f, 15f, 15f, 30f};
		table.setWidths(colwidth);
		
		for(String header : title) {
			PdfPCell cell=new PdfPCell();
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setPadding(10);
			cell.setGrayFill(0.9f);
			cell.setPhrase(new Phrase(header, fontTitle));
			table.addCell(cell);
		}
		table.completeRow();
		
		for(String[] row: rows) {
			for(String data : row) {
				Phrase phrase=new Phrase(data, fontRows);
				PdfPCell cell=new PdfPCell(phrase);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setPaddingTop(20);
				cell.setPaddingRight(30);
				cell.setPaddingBottom(20);
				cell.setPaddingLeft(30);				
				table.addCell(cell);				
			}			
			table.completeRow();
		}
		
		PdfPCell cell4=new PdfPCell(new Phrase("Cell 5"));
		cell4.setColspan(2);
		
		PdfPCell cell5=new PdfPCell(new Phrase("Cell 6"));
		cell5.setColspan(2);
		
		table.addCell(cell4);
		table.addCell(cell5);
		
		doc.addTitle("PDF Table Demo");
		doc.add(table);
		System.out.println("table ���� �Ϸ�");
		
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		doc.close();
	}
  }
}
