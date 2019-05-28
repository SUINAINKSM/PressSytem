package ui;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import model.BookInfo;

public class myEXCEL
{
    private List<BookInfo> date;

    public myEXCEL(List<BookInfo> date)
    {
        this.date = date;
    }

    public void createExcel() throws WriteException, IOException
    {
        OutputStream os = new FileOutputStream("ͼ����һ����EXCEL.xls");
        // ����������
        WritableWorkbook workbook = Workbook.createWorkbook(os);
        // �����µ�һҳ
        WritableSheet sheet = workbook.createSheet("First Sheet", 0);
        // ����Ҫ��ʾ������,����һ����Ԫ�񣬵�һ������Ϊ�����꣬�ڶ�������Ϊ�����꣬����������Ϊ����
        Label shuh = new Label(0, 0, "���");
        sheet.addCell(shuh);
        Label shum = new Label(1, 0, "רҵ");
        sheet.addCell(shum);
        Label zuozhe = new Label(2, 0, "����");
        sheet.addCell(zuozhe);
        Label fenlei = new Label(3, 0, "ͼ�����");
        sheet.addCell(fenlei);
        Label kaiben = new Label(4, 0, "����");
        sheet.addCell(kaiben);
        Label kuchun = new Label(5, 0, "���");
        sheet.addCell(kuchun);
        Label dj = new Label(6, 0, "����");
        sheet.addCell(dj);
        Label mayang = new Label(7, 0, "����");
        sheet.addCell(mayang);

        for (int i = 1; i <= date.size(); i++)
            for (int j = 0; j < 8; j++)
            {
                Label shuju = new Label(j, i, String.valueOf(date.get(i - 1).get(j)));
                sheet.addCell(shuju);
            }

        // �Ѵ���������д�뵽������У����ر������
        workbook.write();
        workbook.close();
        os.close();
    }

}
