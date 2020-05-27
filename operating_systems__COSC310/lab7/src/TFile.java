
public class TFile implements Comparable<TFile>{
    public String filename;
    public int size;
    public int loc;

    public TFile(String filename, int size, int loc)
    {
        this.filename = filename;
        this.size = size;
        this.loc = loc;
    }

    @Override
    public int compareTo(TFile o) {
        return this.filename.compareTo(o.filename);
    }
}


