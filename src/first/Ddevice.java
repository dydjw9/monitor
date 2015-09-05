/*     */ package first;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ public class Ddevice
/*     */ {
/*     */   public int wafer;
/*     */   public int row;
/*     */   public int column;
/*     */   public int design;
/*     */   public int pad;
/*     */   public int id;
/*     */   public int count;
/*     */   public int PTS;
/*     */   public String mode;
/*     */   public String Cstring;
/*     */   public String Scomment;
/*     */   public double Cdouble;
/*     */   public double von;
/*     */   public double voff;
/*     */   public double Rvirgin_2;
/*     */   public double Rvirgin_neg5;
/*     */   public double Rvirgin_5;
/*     */   public double Rform_neg2;
/*     */   public double Vform;
/*     */   public double Rform_neg5;
/*     */   public double Iform;
/*     */   public double Iswitching;
/*     */   public double Ron_5;
/*     */   public double Ron_neg5;
/*     */   public double Roff_5;
/*     */   public double Roff_neg5;
/*     */   public double Rform_neg1;
/*     */   public double via_high;
/*     */   public double via_low;
/*     */   public double m1;
/*     */   public double Icompliance;
/*     */   public double sweep_voltage;
/*  15 */   public double[] voltage = new double[1000];
/*  16 */   public double[] current = new double[1000];
/*  17 */   public double[] resistance = new double[1000];
/*     */ 
/*     */   public Ddevice(String name)
/*     */   {
/*  24 */     int length = name.length();
/*  25 */     int Nwafer = name.indexOf("_T_");
/*  26 */     if (name.indexOf("form") != 0) this.mode = "form";
/*  27 */     else if (name.indexOf("switching") != 0) this.mode = "switching"; else this.mode = "unwanted";
/*     */ 
/*  29 */     int Nrow = name.indexOf("_R");
/*     */ 
/*  31 */     int Npad = name.indexOf("Pad");
/*  32 */     if (isNumeric(name.substring(Nwafer + 4, Nwafer + 5))) {
/*  33 */       this.wafer = Integer.valueOf(name.substring(Nwafer + 3, Nwafer + 5)).intValue();
/*     */ 
/*  35 */       this.design = Integer.valueOf(name.substring(Nwafer + 6, Nwafer + 7)).intValue(); } else {
/*  36 */       this.wafer = Integer.valueOf(name.substring(Nwafer + 3, Nwafer + 4)).intValue();
/*     */ 
/*  38 */       this.design = Integer.valueOf(name.substring(Nwafer + 5, Nwafer + 6)).intValue();
/*     */     }
/*     */ 
/*  41 */     this.row = Integer.valueOf(name.substring(Nrow + 2, Nrow + 3)).intValue();
/*  42 */     if (isNumeric(name.substring(Npad - 6, Npad - 5)))
/*  43 */       this.PTS = Integer.valueOf(name.substring(Npad - 7, Npad - 5)).intValue();
/*  44 */     else this.PTS = Integer.valueOf(name.substring(Npad - 7, Npad - 6)).intValue();
/*  45 */     if (isNumeric(name.substring(Nrow + 5, Nrow + 6)))
/*     */     {
/*  47 */       this.column = Integer.valueOf(name.substring(Nrow + 4, Nrow + 6)).intValue();
/*     */     }
/*     */     else
/*     */     {
/*  51 */       this.column = Integer.valueOf(name.substring(Nrow + 4, Nrow + 5)).intValue();
/*     */     }
/*  53 */     if (isNumeric(name.substring(Npad + 4, Npad + 5)))
/*     */     {
/*  55 */       this.pad = Integer.valueOf(name.substring(Npad + 3, Npad + 5).trim()).intValue();
/*     */     }
/*     */     else
/*     */     {
/*  59 */       this.pad = Integer.valueOf(name.substring(Npad + 3, Npad + 4)).intValue();
/*     */     }
/*  61 */     this.id = (this.wafer * 1000000 + this.row * 100000 + this.column * 1000 + this.design * 100 + this.pad + this.PTS % 2);
/*     */     try
/*     */     {
/*  65 */       String encoding = "GBK";
/*  66 */       File file = new File(name);
/*     */ 
/*  68 */       int i = 0;
/*  69 */       this.count = 0;
/*  70 */       if ((file.isFile()) && (file.exists())) {
/*  71 */         InputStreamReader read = new InputStreamReader(
/*  72 */           new FileInputStream(file), encoding);
/*  73 */         BufferedReader br = new BufferedReader(read);
/*  74 */         String lineTxt = null;
/*  75 */         for (i = 1; i <= 20; i++) {
/*  76 */           this.Cstring = br.readLine();
/*     */ 
/*  78 */           if (i == 8)
/*     */           {
/*  80 */             int Clength = this.Cstring.length();
/*  81 */             this.Cstring = this.Cstring.substring(Clength - 9, Clength - 1);
/*  82 */             this.sweep_voltage = Double.valueOf(this.Cstring).doubleValue();
/*     */           }
/*  84 */           if (i == 10)
/*     */           {
/*  89 */             int Clength = this.Cstring.length();
/*  90 */             this.Cstring = this.Cstring.substring(Clength - 8, Clength - 1);
/*  91 */             this.Cdouble = Double.valueOf(this.Cstring).doubleValue();
/*     */           }
/*     */ 
/*  95 */           if (i == 12)
/*     */           {
/*  99 */             this.Scomment = this.Cstring;
/* 100 */             int Clength = this.Scomment.length();
/* 101 */             this.Scomment = this.Scomment.substring(9, Clength);
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/* 109 */         while ((lineTxt = br.readLine()) != null)
/*     */         {
/* 111 */           length = lineTxt.length();
/* 112 */           String[] strs = lineTxt.split("\\s++");
/* 113 */           this.voltage[this.count] = Double.valueOf(strs[0]).doubleValue();
/*     */ 
/* 115 */           this.current[this.count] = Double.valueOf(strs[1]).doubleValue();
/*     */ 
/* 117 */           this.resistance[this.count] = Double.valueOf(strs[2]).doubleValue();
/* 118 */           this.count += 1;
/*     */         }
/*     */ 
/* 124 */         read.close();
/* 125 */         if (name.indexOf("_M1") != -1)
/*     */         {
/* 127 */           this.m1 = this.resistance[1];
/*     */         }
/* 129 */         if (name.indexOf("VIA_high") != -1)
/*     */         {
/* 131 */           this.via_high = this.resistance[1];
/*     */         }
/* 133 */         if (name.indexOf("VIA_low") != -1)
/*     */         {
/* 135 */           this.via_low = this.resistance[1];
/*     */         }
/* 137 */         if (name.indexOf("Rvirgin") != -1)
/*     */         {
/* 139 */           this.Rvirgin_2 = this.resistance[1];
/*     */         }
/* 141 */         if ((name.indexOf("form") != -1) && (name.indexOf("Rform") == -1))
/*     */         {
/* 143 */           this.Iform = this.Cdouble;
/* 144 */           this.Rvirgin_neg5 = this.resistance[search(-0.5D, 1)];
/* 145 */           this.Vform = this.voltage[search2(-1)];
/* 146 */           this.Rform_neg5 = this.resistance[search(-0.5D, 2)];
/* 147 */           this.Rform_neg1 = this.resistance[search(-0.1D, 2)];
/*     */         }
/*     */ 
/* 150 */         if (name.indexOf("Rform") != -1)
/*     */         {
/* 152 */           this.Rform_neg2 = this.resistance[1];
/*     */         }
/* 154 */         if (name.indexOf("switching") != -1)
/*     */         {
/* 156 */           this.Ron_5 = this.resistance[search(0.5D, 1)];
/*     */ 
/* 158 */           this.Roff_5 = this.resistance[search(0.5D, 2)];
/* 159 */           this.Iswitching = this.Cdouble;
/* 160 */           this.Ron_neg5 = this.resistance[search(-0.5D, 2)];
/* 161 */           this.Roff_neg5 = this.resistance[search(-0.5D, 1)];
/* 162 */           this.von = this.voltage[search2(-1)];
/* 163 */           this.voff = this.voltage[search2(1)];
/*     */         }
/*     */       }
/*     */       else {
/* 167 */         System.out.println("找不到指定的文件");
/*     */       }
/*     */     } catch (Exception e) {
/* 170 */       System.out.println("读取文件内容出错");
/* 171 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static boolean isNumeric(String str)
/*     */   {
/* 188 */     for (int i = 0; i < str.length(); i++)
/*     */     {
/* 190 */       if (!Character.isDigit(str.charAt(i))) {
/* 191 */         return false;
/*     */       }
/*     */     }
/* 194 */     return true;
/*     */   }
/*     */ 
/*     */   public int search(double searchkey, int index)
/*     */   {
/* 200 */     int i = 0; int a = 0;
/* 201 */     for (i = 0; i <= this.count; i++)
/*     */     {
/* 203 */       if (this.voltage[i] - searchkey == 0.0D)
/* 204 */         a++;
/* 205 */       if (a == index)
/*     */       {
/* 207 */         return i;
/*     */       }
/*     */     }
/* 209 */     return i;
/*     */   }
/*     */ 
/*     */   public int search2(int direction)
/*     */   {
/* 215 */     int a = 0;
/* 216 */     double max = Math.abs(this.current[0]); double error = max / 50.0D;
/*     */     int i;
/* 217 */     if (direction == 1)
/*     */     {
/* 219 */       for (i = 0; i <= this.count; i++)
/*     */       {
/* 221 */         if (max + error < Math.abs(this.current[i]))
/*     */         {
/* 223 */           max = Math.abs(this.current[i]); error = max / 100.0D;
/*     */ 
/* 225 */           a = 1;
/*     */         }
/* 230 */         else if (max - this.current[i] < error)
/*     */         {
/* 232 */           a++;
/*     */         }
/*     */         else {
/* 235 */           if (a > 5)
/* 236 */             return i - 1; a = 0;
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/* 245 */       for (i = 0; i <= this.count; i++)
/*     */       {
/* 247 */         if (max + error < Math.abs(this.current[i]))
/*     */         {
/* 249 */           max = Math.abs(this.current[i]); error = max / 100.0D; a = 1;
/*     */         }
/* 252 */         else if (a >= 0)
/*     */         {
/* 254 */           if (max + this.current[i] < error)
/*     */           {
/* 256 */             a++;
/*     */           }
/*     */           else
/*     */           {
/* 260 */             a = 0;
/*     */           }
/* 262 */           if (a == 5)
/*     */           {
/* 264 */             return i - 4;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 269 */     return i;
/*     */   }
/*     */ }
