package model;
import java.text.DecimalFormat;

public class BookInfo
{

    private String shuh;
    private String shum;
    private String zuozhe;
    private String fenlei;
    private String kaiben;
    private int kuchun;
    private float dj;

    public Object get(int i)
    {
        switch (i)
        {
            case 0:
                return shuh;
            case 1:
                return shum;
            case 2:
                return zuozhe;
            case 3:
                return fenlei;
            case 4:
                return kaiben;
            case 5:
                return kuchun;
            case 6:
                return new DecimalFormat("##,##0.00").format(dj);
            case 7:
                return new DecimalFormat("##,##0.00").format(kuchun * dj);
            default:
                break;
        }
        return null;
    }

    public BookInfo(String shuh, String shum, String zuozhe, String fenlei, String kaiben, int kuchun, float dj)
    {
        super();
        this.shuh = shuh;
        this.shum = shum;
        this.zuozhe = zuozhe;
        this.fenlei = fenlei;
        this.kaiben = kaiben;
        this.kuchun = kuchun;
        this.dj = dj;
    }

    public String getShuh()
    {
        return shuh;
    }

    public void setShuh(String shuh)
    {
        this.shuh = shuh;
    }

    public String getShum()
    {
        return shum;
    }

    public void setShum(String shum)
    {
        this.shum = shum;
    }

    public String getZuozhe()
    {
        return zuozhe;
    }

    public void setZuozhe(String zuozhe)
    {
        this.zuozhe = zuozhe;
    }

    public String getFenlei()
    {
        return fenlei;
    }

    public void setFenlei(String fenlei)
    {
        this.fenlei = fenlei;
    }

    public String getKaiben()
    {
        return kaiben;
    }

    public void setKaiben(String kaiben)
    {
        this.kaiben = kaiben;
    }

    public int getKuchun()
    {
        return kuchun;
    }

    public void setKuchun(int kuchun)
    {
        this.kuchun = kuchun;
    }

    public float getDj()
    {
        return dj;
    }

    public void setDj(float dj)
    {
        this.dj = dj;
    }

}
