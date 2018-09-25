package business;
/**
 * @author pdaniel
 */
public class PV  extends Financial {
    public static final String TITLE="Present Value Schedule";
    public static final String AMOUNTDESC = "Lump Sum Amount";
    public static final String RESULTDESC = "Present Value";
    public static final String INTFACTORDESC = "Discount";
    public static final String BEGBALDESC = "Present Value";
    public static final String ENDBALDESC = "Present Value";
    public static final String PRINFACTORDESC = "Value Change";
    private double[] bbal, ifactor;
    
    public PV() {
        super();
    }
    public PV(double a, double r, int t) {
        super(a,r,t);
        calcPV();
    }
    public String getTitle() {
        return PV.TITLE;
    }
    public double getResult() {
        if (!super.isBuilt()) {
            calcPV();            
        }
        return this.bbal[0];
    }
    public String getAmountDesc() {
        return PV.AMOUNTDESC;
    }
    public String getResultDesc() {
        return PV.RESULTDESC;
    }
    public double getBegBal(int mo) {
        if (!super.isBuilt()) { calcPV(); }
        if (mo < 0 || mo > super.getTerm()) { return 0; }
        return this.bbal[mo];
    }
    public String getBegBalDesc() {
        return PV.BEGBALDESC;
    }
    public double getIntFactor(int mo) {
        if (!super.isBuilt()) { calcPV(); }
        if (mo < 0 || mo > super.getTerm()) { return 0; }
        return this.ifactor[mo];
    }
    public String getIntFactorDesc() {
        return PV.INTFACTORDESC;
    }
    public double getPrinFactor(int mo) {
        if (mo < 0 || mo > (super.getTerm()+1)) { return 0; }
        return (this.bbal[mo+1] - this.bbal[mo]);
    }
    public String getPrinFactorDesc() {
        return PV.PRINFACTORDESC;
    }
    public double getEndBal(int mo) {
        if (!super.isBuilt()) { calcPV(); }
        if (mo < 1 || mo > super.getTerm()) { return 0; }
        return this.bbal[mo-1];
    }
    public String getEndBalDesc() {
        return PV.ENDBALDESC;
    }
    private void calcPV() {
        //internal logic for building an annuity...
        //this.fv = 0;
        //double intearned=0;
        this.ifactor = new double[super.getTerm()+1];
        this.bbal = new double[super.getTerm()+1];
        
        for (int i=0; i<super.getTerm()+1; i++) {
            this.bbal[i] = super.getAmt() / 
                   Math.pow((1+super.getRate()/12.0),(super.getTerm() - i));
            this.ifactor[i] = (super.getAmt() - this.bbal[i]);           
        }
        super.setBuilt(true);
    }
    public void run () {
}
}
