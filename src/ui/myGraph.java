package ui;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis3D;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis3D;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.DefaultCategoryDataset;

import model.zhifang;

public class myGraph
{
    private List<zhifang> date;

    public myGraph(List<zhifang> date)
    {
        this.date = date;
    }

    public void create(int year)
    {

        // ��״ͼ����
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        // �������
        for (zhifang dd : date)
            dataset.addValue(dd.getKuchun(), "", dd.getYuefen() + "�·�");

        // ���ɵ���״ͼ
        JFreeChart chart = ChartFactory.createBarChart3D(year+"��"+"ͼ�����ͳ��ͼ��", "�·�", // X��ı�ǩ
                "����", // Y��ı�ǩ
                dataset, // ͼ����ʾ�����ݼ���
                PlotOrientation.VERTICAL, // ͼ�����ʾ��ʽ��ˮƽ���ߴ�ֱ��
                false, // �Ƿ���ʾ�ӱ���
                false, // �Ƿ�������ʾ�ı�ǩ
                false); // �Ƿ�����URL����

        /*
         * ����ͼ���ϵ�����
         */

        // ���������������
        chart.getTitle().setFont(new Font("����", Font.BOLD, 18));

        // ��ȡͼ���������
        CategoryPlot categoryPlot = (CategoryPlot) chart.getPlot();
        categoryPlot.getRenderer().setPaint( new Color(0,0,255)) ;
        // ��ȡX��Ķ���
        CategoryAxis3D categoryAxis3D = (CategoryAxis3D) categoryPlot.getDomainAxis();

        // ��ȡY��Ķ���
        NumberAxis3D numberAxis3D = (NumberAxis3D) categoryPlot.getRangeAxis();

        // ����X���ϵ�����
        categoryAxis3D.setTickLabelFont(new Font("����", Font.BOLD, 10));

        // ����X���������
        categoryAxis3D.setLabelFont(new Font("����", Font.BOLD, 10));

        // ����Y���ϵ�����
        numberAxis3D.setTickLabelFont(new Font("����", Font.BOLD, 10));

        // ����Y���������
        numberAxis3D.setLabelFont(new Font("����", Font.BOLD, 10));

        // �Զ���Y������ʾ�Ŀ̶ȣ���10��Ϊ1��
        numberAxis3D.setAutoTickUnitSelection(false);
        NumberTickUnit unit = new NumberTickUnit(1);
        numberAxis3D.setTickUnit(unit);
        // ��ȡ��ͼ�������
        BarRenderer3D barRenderer3D = (BarRenderer3D) categoryPlot.getRenderer();
        // ��������ͼ�Ŀ��
        barRenderer3D.setMaximumBarWidth(0.07);
        // ��ͼ������ʾ����
        barRenderer3D.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        barRenderer3D.setBaseItemLabelsVisible(true);
        barRenderer3D.setBaseItemLabelFont(new Font("����", Font.BOLD, 10));
        // ��״ͼ���������
   
        categoryAxis3D.setLowerMargin(0.0);
        categoryAxis3D.setCategoryLabelPositions(CategoryLabelPositions.STANDARD);

        File file = new File("ֱ��ͼ.png");
        try
        {
            ChartUtilities.saveChartAsJPEG(file, chart, 800, 600);
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }

}
