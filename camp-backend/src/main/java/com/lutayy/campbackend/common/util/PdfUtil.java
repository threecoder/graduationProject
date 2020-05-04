package com.lutayy.campbackend.common.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;

public class PdfUtil {

    public static boolean writePdf(Map<String, String> contentMap, String temPath, String outPath, String backImagePath){
        PdfReader reader;
        FileOutputStream out;
        ByteArrayOutputStream bos;
        PdfStamper stamper;
        try{
            File file = new File(outPath);
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            if(!file.exists()){
                file.createNewFile();
            }
            out = new FileOutputStream(outPath);
            reader = new PdfReader(temPath);
            bos = new ByteArrayOutputStream();
            stamper = new PdfStamper(reader, bos);
            AcroFields af = stamper.getAcroFields();
            for(String key : contentMap.keySet()){
                String value = contentMap.get(key);
                af.setField(key, value);
            }
            PdfContentByte under = stamper.getUnderContent(1);
            Rectangle signRect = af.getFieldPositions("back_ground").get(0).position;
            float x = signRect.getLeft();
            float y = signRect.getBottom();
            //设置背景图片
            Image jpeg = Image.getInstance(backImagePath);
            jpeg.scaleToFit(signRect.getWidth(), signRect.getHeight());
            jpeg.setAlignment(Image.UNDERLYING); //设置图片的位置，参数Image.UNDERLYING是作为文字的背景显示
            jpeg.setAbsolutePosition(x, y); //设置图片的尽对位
            under.addImage(jpeg);
            //true不可编辑，false可编辑
            stamper.setFormFlattening(false);
            stamper.close();
            Document doc=new Document();
            PdfCopy copy=new PdfCopy(doc, out);
            doc.open();
            doc.add(jpeg);
            PdfImportedPage importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), 1);
            copy.addPage(importPage);
            doc.close();
            reader.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
