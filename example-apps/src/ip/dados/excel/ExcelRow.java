package dados.excel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;

import dados.excel.exception.NotDefinedCellException;

public class ExcelRow {


       private HSSFRow hssfRow;

       public ExcelRow (HSSFRow hssfRow) {
              this.setHSSFRow (hssfRow);
       }

       protected void setHSSFRow (HSSFRow hssfRow) {
            
            if (hssfRow != null) {
                this.hssfRow = hssfRow;
            } else {
            	throw new IllegalArgumentException("A linha passada eh nula");
            }

        }

        public ExcelCell getCell (int cellnum) throws NotDefinedCellException {
            ExcelCell ret = null;
            HSSFCell tmp = this.hssfRow.getCell(cellnum);

            if (tmp != null) {
                ret = new ExcelCell (tmp);
            } else {
            	throw new NotDefinedCellException(String.format("A celula numero %d nao esta definida", cellnum));
            }

            return ret;
        }

}

