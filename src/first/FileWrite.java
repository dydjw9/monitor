/*    */ package first;
/*    */ 
/*    */ import java.io.BufferedWriter;
/*    */ import java.io.File;
/*    */ import java.io.FileNotFoundException;
/*    */ import java.io.FileWriter;
/*    */ import java.io.IOException;
/*    */ import java.io.PrintStream;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class FileWrite
/*    */ {
/*    */   public static void Writetxt(String[][] str, int count, String savepath)
/*    */   {
/* 17 */     StringBuilder stringbuilder = new StringBuilder();
/* 18 */     stringbuilder.append(savepath);
/* 19 */     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
/* 20 */     stringbuilder.append("\\");
/* 21 */     stringbuilder.append(df.format(new Date()).toString());
/* 22 */     stringbuilder.append(".txt");
/*    */ 
/* 24 */     savepath = stringbuilder.toString();
/* 25 */     System.out.println(savepath);
/* 26 */     File fileName = new File(savepath);
/*    */     try
/*    */     {
/* 29 */       fileName.createNewFile();
/*    */ 
/* 31 */       FileWriter output = new FileWriter(savepath);
/* 32 */       BufferedWriter bf = new BufferedWriter(output);
/* 33 */       for (int i = 0; i <= count; i++)
/*    */       {
/* 35 */         for (String l : str[i]) {
/* 36 */           if (l == "") l = " ";
/* 37 */           bf.write(l + "\t");
/*    */         }
/* 39 */         bf.write("\r\n");
/*    */       }
/* 41 */       bf.flush();
/* 42 */       bf.close();
/*    */     } catch (FileNotFoundException e) {
/* 44 */       e.printStackTrace();
/*    */     }
/*    */     catch (IOException e) {
/* 47 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/*    */   public static void WriteFormTxt(String str)
/*    */   {
/*    */     try {
/* 54 */       FileWriter output = new FileWriter("P:/Archive/users/Pan Rui/testing/Memristor/Data_relay/formornot.txt");
/* 55 */       BufferedWriter bf = new BufferedWriter(output);
/*    */ 
/* 57 */       bf.write(str);
/*    */ 
/* 59 */       bf.flush();
/* 60 */       bf.close();
/*    */     } catch (FileNotFoundException e) {
/* 62 */       e.printStackTrace();
/*    */     }
/*    */     catch (IOException e) {
/* 65 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ }
