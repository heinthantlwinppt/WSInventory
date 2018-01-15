package com.ppt.wsinventory.model;

        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

        import java.util.Date;

public class Smith_jobproduct {

    @SerializedName("smith_joborder")
    @Expose
    private String smithJoborder;
    @SerializedName("plength")
    @Expose
    private String plength;
    @SerializedName("weight")
    @Expose
    private Double weight;
    @SerializedName("k")
    @Expose
    private Integer k;
    @SerializedName("p")
    @Expose
    private Integer p;
    @SerializedName("y")
    @Expose
    private Double y;
    @SerializedName("qty")
    @Expose
    private Double qty;
    @SerializedName("reduce_weight")
    @Expose
    private Double reduceWeight;
    @SerializedName("reduce_k")
    @Expose
    private Integer reduceK;
    @SerializedName("reduce_p")
    @Expose
    private Integer reduceP;
    @SerializedName("reduce_y")
    @Expose
    private Double reduceY;
    @SerializedName("target_date")
    @Expose
    private Date targetDate;
    @SerializedName("start_date")
    @Expose
    private Date startDate;
    @SerializedName("end_date")
    @Expose
    private Date endDate;
    @SerializedName("wages")
    @Expose
    private Double wages;
    @SerializedName("remarks")
    @Expose
    private String remarks;
    @SerializedName("row_no")
    @Expose
    private Integer rowNo;
    @SerializedName("is_delete")
    @Expose
    private Boolean isDelete;
    @SerializedName("products")
    @Expose
    private String products;
    @SerializedName("uom")
    @Expose
    private String uom;
    @SerializedName("to_location")
    @Expose
    private String toLocation;

    public String getSmithJoborder() {
        return smithJoborder;
    }

    public void setSmithJoborder(String smithJoborder) {
        this.smithJoborder = smithJoborder;
    }

    public String getPlength() {
        return plength;
    }

    public void setPlength(String plength) {
        this.plength = plength;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Integer getK() {
        return k;
    }

    public void setK(Integer k) {
        this.k = k;
    }

    public Integer getP() {
        return p;
    }

    public void setP(Integer p) {
        this.p = p;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public double getReduceWeight() {
        return reduceWeight;
    }

    public void setReduceWeight(double reduceWeight) {
        this.reduceWeight = reduceWeight;
    }

    public Integer getReduceK() {
        return reduceK;
    }

    public void setReduceK(Integer reduceK) {
        this.reduceK = reduceK;
    }

    public Integer getReduceP() {
        return reduceP;
    }

    public void setReduceP(Integer reduceP) {
        this.reduceP = reduceP;
    }

    public double getReduceY() {
        return reduceY;
    }

    public void setReduceY(double reduceY) {
        this.reduceY = reduceY;
    }

    public Date getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Double getWages() {
        return wages;
    }

    public void setWages(Double wages) {
        this.wages = wages;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getRowNo() {
        return rowNo;
    }

    public void setRowNo(Integer rowNo) {
        this.rowNo = rowNo;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getToLocation() {
        return toLocation;
    }

    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }

}
