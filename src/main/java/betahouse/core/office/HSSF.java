package betahouse.core.office;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yxm on 2017/7/17.
 */
/* HSSF － 提供读写Microsoft Excel XLS格式档案的功能。 */
public class HSSF {
    //指定存储文件夹
    private static final String FOLDER = "office" + File.separator + "excel";

    private HSSFWorkbook wb = null;
    private String folderName = null;
    private String fileName = null;

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public HSSF() {

    }

    public HSSF(String folderName, String fileName) {
        this.folderName = folderName;
        this.fileName = fileName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    //Excel文件保存
    private int save() {
        FileOutputStream out = null;
        String folderPath = FOLDER + File.separator + folderName + File.separator;
        File f = new File(folderPath);
        if (!f.exists()) {
            f.mkdirs();
        }
        try {
            out = new FileOutputStream(folderPath + fileName + ".xls");
            wb.write(out);
            out.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
            return 1;
        }
        return 0;
    }

    public List<Sheet> create(String... sheetName) {
        wb = new HSSFWorkbook();
        List<Sheet> listSheet = new ArrayList<Sheet>();
        for (String name : sheetName) {
            @SuppressWarnings("unused")
            Sheet sheet = wb.createSheet(name);
            listSheet.add(sheet);
        }
        save();
        return listSheet;
    }

    public int open() {
        FileInputStream in = null;
        try {
            String folderPath = FOLDER + File.separator + folderName + File.separator;
            in = new FileInputStream(folderPath + fileName + ".xls");
            POIFSFileSystem fs = new POIFSFileSystem(in);
            wb = new HSSFWorkbook(fs);
            in.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
            create();
            return 1;
        }
        return 0;
    }

    public void insert(int sheetIndex, int x_index, int y_index, String[] fieldName, List list) {
        try {
            if (wb == null) throw new Exception("未打开文件");
            int y;
            Row row = null;
            Sheet sheet = wb.getSheetAt(sheetIndex);
            if (fieldName != null) {
                row = sheet.createRow(x_index++);
                y = y_index;
                for (String name : fieldName) {
                    row.createCell(y++).setCellValue(name);
                }
            }
            for (Object o : list) {
                row = sheet.createRow(x_index++);
                Field[] fields = o.getClass().getDeclaredFields();
                y = y_index;
                for (Field field : fields) {
                    field.setAccessible(true);
                    try {
                        if (field.get(o) != null && !"".equals(field.get(o).toString())) {
                            row.createCell(y++).setCellValue(field.get(o).toString());
                        }
                    } catch (IllegalAccessException e) {
                        logger.error(e.getMessage());
                    }
                }
            }
            save();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void insert(int sheetIndex, int x_index, int y_index, String value) {
        try {
            if (wb == null) throw new Exception("未打开文件");
            Sheet sheet = wb.getSheetAt(sheetIndex);
            sheet.createRow(x_index).createCell(y_index).setCellValue(value);
            save();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void set(int sheetIndex, int x_index, int y_index, String[] fieldName, List list) {
        try {
            if (wb == null) throw new Exception("未打开文件");
            int y;
            Row row = null;
            Cell cell = null;
            Sheet sheet = wb.getSheetAt(sheetIndex);
            if (fieldName != null) {
                row = sheet.getRow(x_index);
                if (row == null) {
                    row = sheet.createRow(x_index);
                }
                x_index++;
                y = y_index;
                for (String name : fieldName) {
                    cell = row.getCell(y);
                    if (cell == null) {
                        cell = row.createCell(y);
                    }
                    cell.setCellValue(name);
                    y++;
                }
            }
            for (Object o : list) {
                row = sheet.getRow(x_index);
                if (row == null) {
                    row = sheet.createRow(x_index);
                }
                x_index++;
                Field[] fields = o.getClass().getDeclaredFields();
                y = y_index;
                for (Field field : fields) {
                    field.setAccessible(true);
                    try {
                        if (field.get(o) != null && !"".equals(field.get(o).toString())) {
                            cell = row.getCell(y);
                            if (cell == null) {
                                cell = row.createCell(y);
                            }
                            cell.setCellValue(field.get(o).toString());
                            y++;
                        }
                    } catch (IllegalAccessException e) {
                        logger.error(e.getMessage());
                    }
                }
            }
            save();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void set(int sheetIndex, int x_index, int y_index, String value) {
        try {
            if (wb == null) throw new Exception("未打开文件");
            Sheet sheet = wb.getSheetAt(sheetIndex);
            Row row = null;
            Cell cell = null;
            row = sheet.getRow(x_index);
            if (row == null) {
                row = sheet.createRow(x_index);
            }
            cell = row.getCell(y_index);
            if (cell == null) {
                cell = row.createCell(y_index);
            }
            cell.setCellValue(value);
            save();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public String get(int sheetIndex, int x_index, int y_index) {
        String str = "";
        try {
            if (wb == null) throw new Exception("未打开文件");
            str = wb.getSheetAt(sheetIndex).getRow(x_index).getCell(y_index).getStringCellValue();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return str;
    }

    public int close() throws Exception {
        save();
        wb = null;
        return 0;
    }
}
