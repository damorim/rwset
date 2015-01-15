package dados.excel;

import org.apache.poi.hssf.usermodel.HSSFCell;

import dados.excel.exception.CellNumberFormatException;
import dados.excel.exception.CellStringFormatException;

public class ExcelCell {


       private HSSFCell hssfCell;

       public ExcelCell (HSSFCell hssfCell) {
              this.setHSSFCell (hssfCell);
       }

       protected void setHSSFCell (HSSFCell hssfCell) {
            
            if (hssfCell != null) {
                this.hssfCell = hssfCell;
            } else {
            	throw new IllegalArgumentException("A celula passada eh nula");
            }
            
        }

        public double getNumericCellValue () throws CellNumberFormatException {
            double ret = 0.0;

            try {
                ret = this.hssfCell.getNumericCellValue ();
            } catch (Exception e) {
                e.printStackTrace();
            	throw new CellNumberFormatException(String.format("O conteúdo da célula numérica não pode ser convertida em um numero", this.hssfCell.getColumnIndex()));
            }

            return ret;
        }

        public String getStringCellValue () throws CellStringFormatException{
            String ret = "";

            try {
                ret = this.hssfCell.getRichStringCellValue().getString();
            } catch (Exception e) {
                throw new CellStringFormatException(String.format("O conteúdo da célula numérica não pode ser convertida em String", this.hssfCell.getColumnIndex()));
            }

            return ret;
        }

}

