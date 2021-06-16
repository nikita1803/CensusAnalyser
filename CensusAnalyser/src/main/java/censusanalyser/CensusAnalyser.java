package censusanalyser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class CensusAnalyser 
{
	/**
	 * This method is use to iterate the entries and return number of entries . 
	 * @param csvFilePath
	 * @return number of entries.
	 * @throws CensusAnalyserException
	 */
    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException 
    {
        try 
        {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            CsvToBeanBuilder<IndiaCensusCSV> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(IndiaCensusCSV.class);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<IndiaCensusCSV> csvToBean = csvToBeanBuilder.build();
            Iterator<IndiaCensusCSV> censusCSVIterator = csvToBean.iterator();;
            int namOfEateries = 0;
            while (censusCSVIterator.hasNext()) 
            {
                namOfEateries++;
                IndiaCensusCSV censusData = censusCSVIterator.next();
            }
            return namOfEateries;
        } 
        catch (IOException e) 
        {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } 
    }
    
    /**
     * This method is use to analyze the Indian state code 
     * @param filePathCSV
     * @return number of entries
     * @throws CensusAnalyserException
     */
    public static int loadCodeData(String filePathCSV) throws CensusAnalyserException 
    {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePathCSV))) 
        {
            CsvToBean<IndiaStateCodeCSV> csvToBean = new CsvToBeanBuilder<IndiaStateCodeCSV>(reader)
									                 .withType(IndiaStateCodeCSV.class)
									                 .withIgnoreLeadingWhiteSpace(true)
									                 .build();
            Iterator<IndiaStateCodeCSV> indiaStateCodeCSVIterator = csvToBean.iterator();
            Iterable<IndiaStateCodeCSV> censusCSVIterable = () -> indiaStateCodeCSVIterator;
            return (int) StreamSupport.stream(censusCSVIterable.spliterator(), false).count();
        } 
        catch (IOException e) 
        {
        	 throw new CensusAnalyserException(e.getMessage(),
                     CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }
}
