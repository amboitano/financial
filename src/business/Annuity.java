package business;
/**
 * @author ptd
 */
public class Annuity extends Financial {
    public static final String TITLE = "Annuity Schedule";
    public static final String AMOUNTDESC = "Deposit Amount";
    public static final String RESULTDESC = "Final Value";
    public static final String INTFACTORDESC = "Int. Earned";
    public static final String BEGBALDESC = "Beg Annuity Value";
    public static final String ENDBALDESC = "End Annuity Value";
    public static final String PRINFACTORDESC = "Deposit";   
    private double[] bbal, iearn, ebal;
    public Annuity() {
        super();
    }
    public Annuity(double d, double r, int t) {
        super(d,r,t);
        calcAnnuity();
    }
    public String getTitle() {
        return Annuity.TITLE;
    }
    public String getAmountDesc() {
        return Annuity.AMOUNTDESC;
    }
    public double getResult() {
        if (!super.isBuilt()) { calcAnnuity(); }
        return this.ebal[super.getTerm()-1];
    }
    public String getResultDesc() {
        return Annuity.RESULTDESC;
    }
    public double getBegBal(int mo) {
        if (!super.isBuilt()) { calcAnnuity(); }
        if (mo < 1 || mo > super.getTerm()) { return 0; }
        return this.bbal[mo-1];
    }
    public String getBegBalDesc() {
        return Annuity.BEGBALDESC;
    }
    public double getIntFactor(int mo) {
        if (!super.isBuilt()) { calcAnnuity(); }
        if (mo < 1 || mo > super.getTerm()) { return 0; }
        return this.iearn[mo-1];
    }
    public String getIntFactorDesc() {
        return Annuity.INTFACTORDESC;
    }
    public double getPrinFactor(int mo) {
        return super.getAmt();
    }
    public String getPrinFactorDesc() {
        return Annuity.PRINFACTORDESC;
    }
    public double getEndBal(int mo) {
        if (!super.isBuilt()) { calcAnnuity(); }
        if (mo < 1 || mo > super.getTerm()) { return 0; }
        return this.ebal[mo-1];
    }
    public String getEndBalDesc() {
        return Annuity.ENDBALDESC;
    }
    private void calcAnnuity() {
        this.bbal = new double[super.getTerm()];
        this.iearn = new double[super.getTerm()];
        this.ebal = new double[super.getTerm()];
        
        bbal[0] = 0;
        for (int i=0; i < super.getTerm(); i++) {
            if (i > 0) {
                this.bbal[i] = this.ebal[i-1];
            }
            this.iearn[i] = 
                    (this.bbal[i] + super.getAmt() )* (super.getRate()/12.0);
            this.ebal[i] = 
                    this.bbal[i] + this.iearn[i] + super.getAmt();
            //this.fv = this.fv + intearned + this.deposit;
        }
        super.setBuilt(true);
    }    
    public void run () {
}
    
}
    
