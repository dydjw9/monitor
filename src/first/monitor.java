/*     */ package first;
/*     */ 
/*     */ import java.awt.Toolkit;
/*     */ import java.io.PrintStream;
/*     */ import java.nio.file.FileSystem;
/*     */ import java.nio.file.FileSystems;
/*     */ import java.nio.file.Path;
/*     */ import java.nio.file.Paths;
/*     */ import java.nio.file.StandardWatchEventKinds;
/*     */ import java.nio.file.WatchEvent;
/*     */ import java.nio.file.WatchEvent.Kind;
/*     */ import java.nio.file.WatchKey;
/*     */ import java.nio.file.WatchService;
/*     */ 
/*     */ public class monitor
/*     */ {
/*     */   public static String monitorpath;
/*  13 */   public static String[][] databuffer = new String[1000][30];
/*  14 */   public static int count = 0;
/*     */ 
/*  19 */   public static void main(String[] args) throws Exception { int tempid = 0;
/*  20 */     Toolkit toolkit = Toolkit.getDefaultToolkit();
/*  21 */     databuffer[0][0] = "0";
/*     */ 
/*  23 */     int formid = 0;
/*  24 */     double tempRvirgin_neg5 = 0.0D;
/*     */ 
/*  26 */     int location = 0;
/*  27 */     initiate();
/*     */ 
/*  29 */     monitorpath = new String("P:/Archive/users/Pan Rui/testing/Memristor/4th SL/test/");
/*     */ 
/*  31 */     WatchService watchService = FileSystems.getDefault().newWatchService();
/*  32 */     Paths.get(monitorpath, new String[0]).register(watchService, new WatchEvent.Kind[] { 
/*  33 */       StandardWatchEventKinds.ENTRY_CREATE });
/*     */     WatchKey key;
/*     */     do { key = watchService.take();
/*  38 */       for (WatchEvent event : key.pollEvents())
/*     */       {
/*  40 */         System.out.println(event.context() + "发生了" + event.kind() + "事件");
/*  41 */         String filepath = monitorpath + event.context();
/*     */ 
/*  43 */         Ddevice device = new Ddevice(filepath);
/*     */ 
/*  46 */         if (tempid != device.id)
/*     */         {
/*  48 */           location = count + 1;
/*  49 */           count += 1;
/*  50 */           tempid = device.id;
/*  51 */           databuffer[location][1] = Integer.toString(device.wafer);
/*  52 */           databuffer[location][2] = Integer.toString(device.row);
/*  53 */           databuffer[location][3] = Integer.toString(device.column);
/*  54 */           databuffer[location][4] = Integer.toString(device.design);
/*  55 */           databuffer[location][5] = Integer.toString(device.pad);
/*  56 */           databuffer[location][6] = Integer.toString(device.PTS);
/*     */         }
/*     */ 
/*  60 */         databuffer[location][0] = Integer.toString(device.id);
/*  61 */         if (filepath.indexOf("_M1") != -1)
/*     */         {
/*  63 */           databuffer[location][8] = Double.toString(device.resistance[1]);
/*     */         }
/*  65 */         if (filepath.indexOf("VIA_high") != -1)
/*     */         {
/*  67 */           databuffer[location][10] = Double.toString(device.resistance[1]);
/*     */         }
/*  69 */         if (filepath.indexOf("VIA_low") != -1)
/*     */         {
/*  71 */           databuffer[location][9] = Double.toString(device.resistance[1]);
/*     */         }
/*  73 */         if (filepath.indexOf("Rvirgin") != -1) {
/*  74 */           System.out.println("Rvirgin" + device.Rvirgin_2);
/*  75 */           databuffer[location][11] = Double.toString(device.resistance[1]);
/*     */         }
/*  77 */         if ((filepath.indexOf("form") != -1) && (filepath.indexOf("Rform") == -1)) {
/*  78 */           System.out.println("form");
/*  79 */           databuffer[location][7] = Double.toString(device.Rform_neg1);
/*  80 */           databuffer[location][15] = Double.toString(device.sweep_voltage);
/*     */ 
/*  82 */           databuffer[location][12] = Double.toString(device.Rvirgin_neg5);
/*     */ 
/*  84 */           if (formid != device.id) { formid = device.id; tempRvirgin_neg5 = device.Rvirgin_neg5; }
/*  85 */           double percent = (tempRvirgin_neg5 - device.Rform_neg5) / tempRvirgin_neg5;
/*  86 */           double percent2 = (tempRvirgin_neg5 - device.Rform_neg1) / tempRvirgin_neg5;
/*  87 */           System.out.println("percent is " + percent + " RForm_neg1 is " + device.Rform_neg1);
/*  88 */           databuffer[location][16] = Double.toString(device.Cdouble);
/*  89 */           databuffer[location][18] = Double.toString(device.Vform);
/*     */           String formnum;
/*  90 */           if ((percent > 0.9D) && (percent2 > 0.9D)) {
/*  91 */             databuffer[location][17] = "yes";
/*  92 */            formnum = "1";
/*  93 */             toolkit.beep();
/*     */           }
/*     */           else
/*     */           {
/*     */           
/*  96 */             if (percent > 0.1D) {
/*  97 */               databuffer[location][17] = "weak"; formnum = "0"; } else {
/*  98 */               databuffer[location][17] = "no"; formnum = "0";
/*     */             }
/*     */           }
/* 100 */           FileWrite.WriteFormTxt(formnum);
/*     */         }
/*     */ 
/* 103 */         if (filepath.indexOf("Rform") != -1) {
/* 104 */           System.out.println("Rform " + device.Rform_neg2);
/* 105 */           databuffer[location][19] = Double.toString(device.resistance[1]);
/*     */         }
/* 107 */         if (filepath.indexOf("switching") != -1)
/*     */         {
/* 110 */           double percent = (device.Roff_neg5 - device.Ron_neg5) / device.Roff_neg5;
/* 111 */           double percent2 = (device.Roff_5 - device.Ron_5) / device.Roff_5;
/* 112 */           databuffer[location][22] = Double.toString(device.Cdouble);
/* 113 */           databuffer[location][24] = device.Scomment;
/* 114 */           databuffer[location][8] = Double.toString(device.Ron_5);
/* 115 */           databuffer[location][9] = Double.toString(device.Roff_5);
/* 116 */           databuffer[location][10] = Double.toString(device.Ron_neg5);
/* 117 */           databuffer[location][25] = Double.toString(device.Roff_neg5);
/* 118 */           databuffer[location][26] = Double.toString(device.von);
/* 119 */           databuffer[location][27] = Double.toString(device.voff);
/* 120 */           databuffer[location][21] = Double.toString(device.sweep_voltage);
/*     */           String switnum;
/*     */         
/* 121 */           if ((percent > 0.3D) && (percent2 > 0.3D)) {
/* 122 */             databuffer[location][20] = "yes"; switnum = "1";
/*     */           }
/*     */           else
/*     */           {
/*     */          
/* 125 */             if (percent > 0.1D) {
/* 126 */               databuffer[location][20] = "weak"; switnum = "0"; } else {
/* 127 */               databuffer[location][20] = "no"; switnum = "0";
/*     */             }
/*     */           }
/* 130 */           System.out.println("switching percent is " + percent + " " + percent2 + "Roffneg5 is " + device.Roff_neg5);
/* 131 */           System.out.println("Roff_neg5 is " + device.Roff_neg5 + " Ron_neg5 is " + device.Ron_neg5);
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 138 */     while (key.reset());
/*     */   }
/*     */ 
/*     */   public static int searchindex(int id)
/*     */   {
/* 149 */     int i = 0;
/* 150 */     System.out.println("icount" + count);
/* 151 */     for (i = 0; i < count; i++)
/*     */     {
/* 153 */       if (id == Integer.valueOf(databuffer[i][0]).intValue()) {
/* 154 */         return i;
/*     */       }
/*     */     }
/* 157 */     return i;
/*     */   }
/*     */ 
/*     */   public static void warning() {
/*     */   }
/*     */ 
/*     */   public static void initiate() {
/* 164 */     databuffer[0][0] = "ID";
/* 165 */     databuffer[0][1] = "Wafer";
/* 166 */     databuffer[0][2] = "Row";
/* 167 */     databuffer[0][3] = "Column";
/* 168 */     databuffer[0][4] = "Design";
/* 169 */     databuffer[0][5] = "Pad";
/* 170 */     databuffer[0][6] = "Device";
/* 171 */     databuffer[0][7] = "Vform";
/* 172 */     databuffer[0][8] = "Ron @0.5V(ohms)";
/* 173 */     databuffer[0][9] = "Roff @0.5V(ohms)";
/* 174 */     databuffer[0][10] = "Ron @ -0.5V(ohms)";
/* 175 */     databuffer[0][11] = "Rvirgin@0.2";
/* 176 */     databuffer[0][12] = "Rvirgin@0.5";
/* 177 */     databuffer[0][13] = "Vread(V)";
/* 178 */     databuffer[0][14] = "Icomp";
/* 179 */     databuffer[0][15] = "form sweep(V)";
/* 180 */     databuffer[0][16] = "Icompf(mA)";
/* 181 */     databuffer[0][17] = "Formed?";
/* 182 */     databuffer[0][18] = "Vform ";
/* 183 */     databuffer[0][19] = "Rform";
/* 184 */     databuffer[0][20] = "Switchable?";
/* 185 */     databuffer[0][21] = "Vsweep(V)";
/* 186 */     databuffer[0][22] = "Icomp(mA)";
/* 187 */     databuffer[0][23] = "remark";
/* 188 */     databuffer[0][24] = "Comment";
/* 189 */     databuffer[0][25] = "Roff @ -0.5V(ohms)";
/* 190 */     databuffer[0][26] = "Von (V)";
/* 191 */     databuffer[0][27] = "Voff (V)";
/*     */   }
/*     */ }
