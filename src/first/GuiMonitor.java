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
/*     */ public class GuiMonitor
/*     */   implements Runnable
/*     */ {
/*     */   public static String monitorpath;
/*  15 */   public static String[][] databuffer = new String[1000][30];
/*  16 */   public static int count = 0;
/*     */   private NetBeanFrame NBeanFrame;
/*     */   private StringBuilder stringbuilder;
/*     */   private boolean RunKey;
/*     */   private String DeviceType;
/*     */   private String PassKey;
/*     */   private boolean IsFind;
/*     */ 
/*     */   public GuiMonitor(NetBeanFrame n)
/*     */   {
/*  25 */     this.NBeanFrame = n;
/*  26 */     this.RunKey = true;
/*  27 */     initiate();
/*     */   }
/*     */ 
/*     */   public void run()
/*     */   {
/*  33 */     int tempid = 0;
/*  34 */     Toolkit toolkit = Toolkit.getDefaultToolkit();
/*  35 */     databuffer[0][0] = "0";
/*     */ 
/*  37 */     int formid = 0;
/*  38 */     double tempRvirgin_neg5 = 0.0D;
/*     */ 
/*  40 */     int location = 0;
/*  41 */     monitor.initiate();
/*  42 */     this.stringbuilder = new StringBuilder();
/*     */     try
/*     */     {
/*  47 */       WatchService watchService = FileSystems.getDefault().newWatchService();
/*  48 */       Paths.get(monitorpath, new String[0]).register(watchService, new WatchEvent.Kind[] { 
/*  49 */         StandardWatchEventKinds.ENTRY_CREATE });
/*     */ 
/*  51 */       while (this.RunKey)
/*     */       {
/*  53 */         WatchKey key = watchService.take();
/*  54 */         for (WatchEvent event : key.pollEvents())
/*     */         {
/*  60 */           String filepath = monitorpath + event.context();
/*     */ 
/*  62 */           Ddevice device = new Ddevice(filepath);
/*  63 */           filepath = event.context().toString();
/*  64 */           System.out.println(filepath);
/*  65 */           System.out.println(Integer.toString(filepath.indexOf("Rform")));
/*  66 */           this.IsFind = false; if (tempid != device.id)
/*     */           {
/*  68 */             this.stringbuilder = new StringBuilder();
/*  69 */             location = count + 1;
/*  70 */             count += 1;
/*  71 */             tempid = device.id;
/*  72 */             databuffer[location][1] = Integer.toString(device.wafer);
/*  73 */             databuffer[location][2] = Integer.toString(device.row);
/*  74 */             databuffer[location][3] = Integer.toString(device.column);
/*  75 */             databuffer[location][4] = Integer.toString(device.design);
/*  76 */             databuffer[location][5] = Integer.toString(device.pad);
/*  77 */             databuffer[location][6] = Integer.toString(device.PTS);
/*     */           }
/*     */ 
/*  81 */           databuffer[location][0] = Integer.toString(device.id);
/*  82 */           this.stringbuilder.append(Integer.toString(device.id));
/*  83 */           this.stringbuilder.append("\n");
/*  84 */           if (filepath.indexOf("_M1") != -1)
/*     */           {
/*  86 */             databuffer[location][8] = Double.toString(device.resistance[1]);
/*     */           }
/*  88 */           if (filepath.indexOf("VIA_high") != -1)
/*     */           {
/*  90 */             databuffer[location][10] = Double.toString(device.resistance[1]);
/*     */           }
/*  92 */           if (filepath.indexOf("VIA_low") != -1)
/*     */           {
/*  94 */             databuffer[location][9] = Double.toString(device.resistance[1]);
/*     */           }
/*  96 */           if (filepath.indexOf("Rvirgin") != -1) {
/*  97 */             this.PassKey = "Pass";
/*  98 */             this.DeviceType = "Rvirgin";
/*  99 */             this.IsFind = true;
/*     */ 
/* 101 */             this.stringbuilder.append("Rvirgin" + device.Rvirgin_2);
/* 102 */             this.stringbuilder.append("\n");
/*     */ 
/* 105 */             databuffer[location][11] = Double.toString(device.resistance[1]);
/*     */           }
/* 107 */           if ((filepath.indexOf("form") != -1) && (filepath.indexOf("Rform") == -1))
/*     */           {
/* 109 */             this.IsFind = true;
/* 110 */             this.DeviceType = "form";
/* 111 */             databuffer[location][7] = Double.toString(device.Rform_neg1);
/* 112 */             databuffer[location][15] = Double.toString(device.sweep_voltage);
/*     */ 
/* 114 */             databuffer[location][12] = Double.toString(device.Rvirgin_neg5);
/*     */ 
/* 116 */             if (formid != device.id) { formid = device.id; tempRvirgin_neg5 = device.Rvirgin_neg5; }
/* 117 */             double percent = (tempRvirgin_neg5 - device.Rform_neg5) / tempRvirgin_neg5;
/* 118 */             double percent2 = (tempRvirgin_neg5 - device.Rform_neg1) / tempRvirgin_neg5;
/*     */ 
/* 122 */             this.stringbuilder.append("percent is ");
/* 123 */             this.stringbuilder.append(Double.valueOf(percent).doubleValue());
/* 124 */             this.stringbuilder.append(" RForm_neg1 is ");
/*     */ 
/* 126 */             this.stringbuilder.append(Double.valueOf(device.Rform_neg1).doubleValue());
/* 127 */             this.stringbuilder.append("\n");
/*     */ 
/* 131 */             databuffer[location][16] = Double.toString(device.Cdouble);
/* 132 */             databuffer[location][18] = Double.toString(device.Vform);
/* 133 */             if ((percent > 0.9D) && (percent2 > 0.9D)) {
/* 134 */               databuffer[location][17] = "yes";
/* 135 */               this.PassKey = "Pass";
/* 136 */               String formnum = "1";
/* 137 */               toolkit.beep();
/*     */             }
/* 140 */             else if (percent > 0.1D) {
/* 141 */               databuffer[location][17] = "weak"; String formnum = "0";
/* 142 */               this.PassKey = "Weak"; } else {
/* 143 */               databuffer[location][17] = "no"; String formnum = "0";
/* 144 */               this.PassKey = "Fail";
/*     */             }
/*     */ 
/*     */           }
/*     */ 
/* 149 */           if ((filepath.indexOf("Rform") != -1) && (!this.IsFind)) {
/* 150 */             this.IsFind = true;
/* 151 */             this.PassKey = "Pass";
/* 152 */             this.DeviceType = "Rform";
/*     */ 
/* 154 */             this.stringbuilder.append("Rform " + device.Rform_neg2);
/* 155 */             this.stringbuilder.append("\n");
/*     */ 
/* 158 */             databuffer[location][19] = Double.toString(device.resistance[1]);
/*     */           }
/* 160 */           if ((filepath.indexOf("switching") != -1) && (!this.IsFind))
/*     */           {
/* 162 */             this.IsFind = true;
/* 163 */             this.DeviceType = "switching";
/*     */ 
/* 166 */             double percent = (device.Roff_neg5 - device.Ron_neg5) / device.Roff_neg5;
/* 167 */             double percent2 = (device.Roff_5 - device.Ron_5) / device.Roff_5;
/* 168 */             databuffer[location][22] = Double.toString(device.Cdouble);
/* 169 */             databuffer[location][24] = device.Scomment;
/* 170 */             databuffer[location][8] = Double.toString(device.Ron_5);
/* 171 */             databuffer[location][9] = Double.toString(device.Roff_5);
/* 172 */             databuffer[location][10] = Double.toString(device.Ron_neg5);
/* 173 */             databuffer[location][25] = Double.toString(device.Roff_neg5);
/* 174 */             databuffer[location][26] = Double.toString(device.von);
/* 175 */             databuffer[location][27] = Double.toString(device.voff);
/* 176 */             databuffer[location][21] = Double.toString(device.sweep_voltage);
/* 177 */             if ((percent > 0.3D) && (percent2 > 0.3D)) {
/* 178 */               databuffer[location][20] = "yes"; String switnum = "1";
/* 179 */               this.PassKey = "Pass";
/*     */             }
/* 182 */             else if (percent > 0.1D) {
/* 183 */               databuffer[location][20] = "weak"; String switnum = "0";
/* 184 */               this.PassKey = "Weak"; } else {
/* 185 */               databuffer[location][20] = "no"; String switnum = "0";
/* 186 */               this.PassKey = "Fail";
/*     */             }
/*     */ 
/* 191 */             this.stringbuilder.append("switching percent is ");
/* 192 */             this.stringbuilder.append(Double.valueOf(percent).toString());
/* 193 */             this.stringbuilder.append("And");
/* 194 */             this.stringbuilder.append(Double.valueOf(percent2).toString());
/* 195 */             this.stringbuilder.append("\n");
/* 196 */             this.stringbuilder.append("Roffneg5 is ");
/* 197 */             this.stringbuilder.append(Double.valueOf(device.Roff_neg5).toString());
/* 198 */             this.stringbuilder.append("\n");
/* 199 */             this.stringbuilder.append(" Ron_neg5 is ");
/* 200 */             this.stringbuilder.append(Double.valueOf(device.Ron_neg5).toString());
/* 201 */             this.stringbuilder.append("\n");
/*     */           }
/*     */ 
/* 212 */           UpdateDisplay(device);
/* 213 */           if (!key.reset())
/*     */           {
/*     */             break;
/*     */           }
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 224 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static int searchindex(int id)
/*     */   {
/* 231 */     int i = 0;
/* 232 */     System.out.println("icount" + count);
/* 233 */     for (i = 0; i < count; i++)
/*     */     {
/* 235 */       if (id == Integer.valueOf(databuffer[i][0]).intValue()) {
/* 236 */         return i;
/*     */       }
/*     */     }
/* 239 */     return i;
/*     */   }
/*     */ 
/*     */   public static void warning() {
/*     */   }
/*     */ 
/*     */   public static void initiate() {
/* 246 */     databuffer[0][0] = "ID";
/* 247 */     databuffer[0][1] = "Wafer";
/* 248 */     databuffer[0][2] = "Row";
/* 249 */     databuffer[0][3] = "Column";
/* 250 */     databuffer[0][4] = "Design";
/* 251 */     databuffer[0][5] = "Pad";
/* 252 */     databuffer[0][6] = "Device";
/* 253 */     databuffer[0][7] = "Vform";
/* 254 */     databuffer[0][8] = "Ron @0.5V(ohms)";
/* 255 */     databuffer[0][9] = "Roff @0.5V(ohms)";
/* 256 */     databuffer[0][10] = "Ron @ -0.5V(ohms)";
/* 257 */     databuffer[0][11] = "Rvirgin@0.2";
/* 258 */     databuffer[0][12] = "Rvirgin@0.5";
/* 259 */     databuffer[0][13] = "Vread(V)";
/* 260 */     databuffer[0][14] = "Icomp";
/* 261 */     databuffer[0][15] = "form sweep(V)";
/* 262 */     databuffer[0][16] = "Icompf(mA)";
/* 263 */     databuffer[0][17] = "Formed?";
/* 264 */     databuffer[0][18] = "Vform ";
/* 265 */     databuffer[0][19] = "Rform";
/* 266 */     databuffer[0][20] = "Switchable?";
/* 267 */     databuffer[0][21] = "Vsweep(V)";
/* 268 */     databuffer[0][22] = "Icomp(mA)";
/* 269 */     databuffer[0][23] = "remark";
/* 270 */     databuffer[0][24] = "Comment";
/* 271 */     databuffer[0][25] = "Roff @ -0.5V(ohms)";
/* 272 */     databuffer[0][26] = "Von (V)";
/* 273 */     databuffer[0][27] = "Voff (V)";
/*     */   }
/*     */ 
/*     */   public void Stop()
/*     */   {
/* 281 */     this.RunKey = false;
/* 282 */     FileWrite.Writetxt(databuffer, count, this.NBeanFrame.getSavePath());
/*     */   }
/*     */ 
/*     */   public void Start() {
/* 286 */     this.RunKey = true;
/* 287 */     monitorpath = this.NBeanFrame.getMonitorPath() + "\\";
/*     */   }
/*     */ 
/*     */   public void UpdateDisplay(Ddevice device)
/*     */   {
/* 293 */     this.NBeanFrame.setType(this.DeviceType);
/* 294 */     this.NBeanFrame.setID(String.valueOf(device.id));
/* 295 */     this.NBeanFrame.setDesign(String.valueOf(device.design));
/* 296 */     this.NBeanFrame.setPad(String.valueOf(device.pad));
/* 297 */     this.NBeanFrame.setPass(this.PassKey);
/* 298 */     this.NBeanFrame.setStatus(this.stringbuilder.toString());
/* 299 */     this.NBeanFrame.UpdateDisplay();
/*     */   }
/*     */ }
