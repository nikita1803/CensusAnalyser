package censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class IndiaStateCodeCSV 
{
	 	@CsvBindByName(column = "SrNo", required = true)
	    public String SrNo;

	    @CsvBindByName(column = "StateName", required = true)
	    public String stateName;

	    @CsvBindByName(column = "TIN", required = true)
	    public String tin;

	    @CsvBindByName(column = "StateCode",required = true)
	    public String stateCode;

	    /**
	     * this is use to print the values .
	     */
	    @Override
	    public String toString() 
	    {
	        return "IndiaStateCodeCSV{" +
	                "SrNo='" + SrNo + '\'' +
	                ", StateName='" + stateName + '\'' +
	                ", TIN='" + tin + '\'' +
	                ", StateCode='" + stateCode + '\'' +
	                '}';
	    }
}
