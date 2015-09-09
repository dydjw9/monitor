/*     */ package first;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Container;
/*     */ import java.awt.EventQueue;
/*     */ import java.awt.Font;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.File;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.GroupLayout.Alignment;
/*     */ import javax.swing.GroupLayout.ParallelGroup;
/*     */ import javax.swing.GroupLayout.SequentialGroup;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JFileChooser;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTextArea;
/*     */ import javax.swing.JTextField;
		  import javax.swing.LayoutStyle;
/*     */ import javax.swing.LayoutStyle.ComponentPlacement;
		  import javax.swing.LayoutStyle.ComponentPlacement;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.UIManager.LookAndFeelInfo;
/*     */ import javax.swing.UnsupportedLookAndFeelException;
/*     */ import javax.swing.filechooser.FileSystemView;
/*     */ 
/*     */ public class NetBeanFrame extends JFrame
/*     */ {
/*     */   private JLabel Deisign;
/*     */   private JTextField DesignText;
/*     */   private JLabel IDLabel;
/*     */   private JTextField IDText;
/*     */   private JLabel MonitorAddress;
/*     */   private JLabel PadLabel;
/*     */   private JTextField PadText;
/*     */   private JButton Pass;
/*     */   private JButton Path;
/*     */   private JButton Run;
            private JButton PassControl;
/*     */   private JButton SaveButton;
/*     */   private JLabel TypeLabel;
/*     */   private JTextField TypeText;
/*     */   private JScrollPane jScrollPane1;
/*     */   private JTextArea jTextArea;
/*     */   private String SavePath;
/*     */   private String MonitorPath;
/*     */   private Thread thread;
/*     */   private boolean IsRun;
/*     */   private String SID;
/*     */   private String SType;
/*     */   private String SDesign;
/*     */   private String SPad;
/*     */   private String SPass;
/*     */   private String Status;
/*     */   private GuiMonitor guimoni;
/*     */ 
/*     */   public NetBeanFrame()
/*     */   {
/*  24 */     initComponents();
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/*  36 */     FileSystemView fsv = FileSystemView.getFileSystemView();
/*     */ 
/*  39 */     this.SavePath = fsv.getHomeDirectory().getPath();
/*  40 */     this.guimoni = new GuiMonitor(this);
/*  41 */     this.IsRun = false;
/*  42 */     this.MonitorAddress = new JLabel();
/*  43 */     this.Path = new JButton();
/*  44 */     this.IDLabel = new JLabel();
/*  45 */     this.IDText = new JTextField();
/*  46 */     this.TypeLabel = new JLabel();
/*  47 */     this.TypeText = new JTextField();
/*  48 */     this.Deisign = new JLabel();
/*  49 */     this.DesignText = new JTextField();
/*  50 */     this.PadLabel = new JLabel();
/*  51 */     this.PadText = new JTextField();
/*  52 */     this.Pass = new JButton();
			  
/*  53 */     this.jScrollPane1 = new JScrollPane();
/*  54 */     this.jTextArea = new JTextArea();
/*  55 */     this.Run = new JButton();
/*  56 */     this.SaveButton = new JButton();
/*     */ 
/*  58 */     setDefaultCloseOperation(3);
/*     */ 
/*  60 */     this.MonitorAddress.setText("Monitor Address");
/*     */ 
/*  62 */     this.Path.setText("Path");
/*  63 */     this.Path.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/*  65 */         NetBeanFrame.this.PathActionPerformed(evt);
/*     */       }
/*     */     });
/*  69 */     this.IDLabel.setText("ID");
/*     */ 
/*  71 */     this.IDText.setText("IdText");
/*     */ 
/*  73 */     this.TypeLabel.setText("Type");
/*     */ 
/*  75 */     this.TypeText.setText("Type");
/*     */ 
/*  77 */     this.Deisign.setText("Design");
/*  78 */     this.Deisign.setToolTipText("");
/*     */ 
/*  80 */     this.DesignText.setText("DesignText");
/*     */ 
/*  82 */     this.PadLabel.setText("Pad");
/*  83 */     this.PadLabel.setToolTipText("");
/*     */ 
/*  85 */     this.PadText.setText("PadText");
/*     */ 
/*  87 */     this.Pass.setFont(new Font("Bookman Old Style", 1, 18));
/*  88 */     this.Pass.setText("Pass");
/*     */ 
/*  90 */     this.jTextArea.setColumns(20);
/*  91 */     this.jTextArea.setRows(5);
/*  92 */     this.jScrollPane1.setViewportView(this.jTextArea);
/*     */ 
			
/*  94 */     this.Run.setText("Run");
/*  95 */     this.Run.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/*  97 */         NetBeanFrame.this.RunActionPerformed(evt);
/*     */       }
/*     */     });
			  this.Pass.setText("Pass");
/*  95 */     this.Pass.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/*  97 */         NetBeanFrame.this.PassActionPerformed(evt);
/*     */       }
/*     */     });
/* 101 */     this.SaveButton.setText("Set Saving Path");
/* 102 */     this.SaveButton.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 104 */         NetBeanFrame.this.SaveActionPerformed(evt);
/*     */       }
/*     */     });
/* 108 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 109 */     getContentPane().setLayout(layout);
/* 110 */     layout.setHorizontalGroup(
/* 111 */       layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 112 */       .addGroup(layout.createSequentialGroup()
/* 113 */       .addContainerGap()
/* 114 */       .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 115 */       .addGroup(layout.createSequentialGroup()
/* 116 */       .addComponent(this.jScrollPane1, -2, 353, -2)
/* 117 */       .addGap(18, 18, 18)
/* 118 */       .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
/* 119 */       .addComponent(this.Run, -1, -1, 32767)

/* 120 */       .addComponent(this.SaveButton, -1, 96, 32767))
/* 121 */       .addContainerGap(-1, 32767))
/* 122 */       .addGroup(layout.createSequentialGroup()
/* 123 */       .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
/* 124 */       .addComponent(this.MonitorAddress, -2, 325, -2)
/* 125 */       .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
/* 126 */       .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
/* 127 */       .addGroup(layout.createSequentialGroup()
/* 128 */       .addComponent(this.IDLabel, -2, 52, -2)
/* 129 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 130 */       .addComponent(this.IDText))
/* 131 */       .addGroup(layout.createSequentialGroup()
/* 132 */       .addComponent(this.Deisign, -2, 52, -2)
/* 133 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 134 */       .addComponent(this.DesignText, -2, -1, -2)))
/* 135 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767)
/* 136 */       .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
/* 137 */       .addGroup(layout.createSequentialGroup()
/* 138 */       .addComponent(this.PadLabel, -2, 52, -2)
/* 139 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 140 */       .addComponent(this.PadText))

/* 141 */       .addGroup(layout.createSequentialGroup()
/* 142 */       .addComponent(this.TypeLabel, -2, 52, -2)
/* 143 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 144 */       .addComponent(this.TypeText, -2, 67, -2)))))
/* 145 */       .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 146 */       .addGroup(layout.createSequentialGroup()
/* 147 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767)
/* 148 */       .addComponent(this.Pass, -2, 96, -2)
				
/* 149 */       .addGap(48, 48, 48))

              
/* 149 */     

/* 150 */       .addGroup(layout.createSequentialGroup()
/* 151 */       .addGap(58, 58, 58)
/* 152 */       .addComponent(this.Path)
/* 153 */       .addContainerGap(59, 32767)))))));
/*     */ 
/* 155 */     layout.setVerticalGroup(
/* 156 */       layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 157 */       .addGroup(layout.createSequentialGroup()
/* 158 */       .addGap(18, 18, 18)
/* 159 */       .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 160 */       .addComponent(this.MonitorAddress)
/* 161 */       .addComponent(this.Path))
/* 162 */       .addGap(18, 18, 18)
/* 163 */       .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 164 */       .addGroup(layout.createSequentialGroup()
/* 165 */       .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 166 */       .addComponent(this.IDLabel, -2, 25, -2)
/* 167 */       .addComponent(this.IDText, -2, -1, -2)
/* 168 */       .addComponent(this.TypeLabel, -2, 25, -2)
/* 169 */       .addComponent(this.TypeText, -2, -1, -2))
/* 170 */       .addGap(18, 18, 18)
/* 171 */       .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 172 */       .addComponent(this.Deisign, -2, 25, -2)
/* 173 */       .addComponent(this.DesignText, -2, -1, -2)
/* 174 */       .addComponent(this.PadLabel, -2, 25, -2)
/* 175 */       .addComponent(this.PadText, -2, -1, -2)))
/* 176 */       .addComponent(this.Pass, -2, 63, -2))
/* 177 */       .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 178 */       .addGroup(layout.createSequentialGroup()
/* 179 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 180 */       .addComponent(this.jScrollPane1, -2, 225, -2))
/* 181 */       .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
/* 182 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767)
/* 183 */       .addComponent(this.Run, -2, 59, -2)

/* 184 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 185 */       .addComponent(this.SaveButton, -2, 53, -2)))
			
/* 186 */       .addContainerGap(26, 32767)));
/*     */ 
/* 189 */     setBounds(0, 0, 525, 418);
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*     */     try
/*     */     {
/* 205 */       for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
/* 206 */         if ("Nimbus".equals(info.getName())) {
/* 207 */           UIManager.setLookAndFeel(info.getClassName());
/* 208 */           break;
/*     */         }
/*     */     }
/*     */     catch (ClassNotFoundException ex) {
/* 212 */       Logger.getLogger(NetBeanFrame.class.getName()).log(Level.SEVERE, null, ex);
/*     */     } catch (InstantiationException ex) {
/* 214 */       Logger.getLogger(NetBeanFrame.class.getName()).log(Level.SEVERE, null, ex);
/*     */     } catch (IllegalAccessException ex) {
/* 216 */       Logger.getLogger(NetBeanFrame.class.getName()).log(Level.SEVERE, null, ex);
/*     */     } catch (UnsupportedLookAndFeelException ex) {
/* 218 */       Logger.getLogger(NetBeanFrame.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/*     */ 
/* 223 */     EventQueue.invokeLater(new Runnable() {
/*     */       public void run() {
/* 225 */         new NetBeanFrame().setVisible(true);
/*     */       }
/*     */     });
/*     */   }
/*     */ 
/*     */   private void PathActionPerformed(ActionEvent evt)
/*     */   {
/* 265 */     this.MonitorPath = selectpath();
/* 266 */     this.MonitorAddress.setText(this.MonitorPath);
/*     */   }
/*     */ 
/*     */   private void RunActionPerformed(ActionEvent evt) {
/* 270 */     if (!this.IsRun)
/*     */     {
/* 272 */       this.thread = new Thread(this.guimoni);
/* 273 */       this.guimoni.Start();
/* 274 */       this.thread.start();
/* 275 */       this.IsRun = true;
/* 276 */       this.Run.setText("Pause");
/*     */     }
/*     */     else
/*     */     {
/* 280 */       this.IsRun = false;
/* 281 */       this.guimoni.Stop();
/* 282 */       this.Run.setText("Run");
/*     */     }
/*     */   }
	private void PassActionPerformed(ActionEvent evt)
				{String[] passtype={"Pass","Weak","Fail"};
				int i=0;
				while(this.Pass.getText()!=passtype[i])
			{
					i=i+1;
					if(i==3)
						i=0;
				}
				if(i==2)
					i=0; 
				else i++;
				
				this.SPass=passtype[i];
				this.guimoni.ChangePassType(passtype[i]);
				
				this.UpdateDisplay();
				}
/*     */ 
/*     */   private void SaveActionPerformed(ActionEvent evt)
/*     */   {
/* 288 */     this.SavePath = selectpath();
/*     */   }
/*     */ 
/*     */   private String selectpath()
/*     */   {
/* 307 */     JFileChooser fd = new JFileChooser();
/* 308 */     fd.setMultiSelectionEnabled(true);
/* 309 */     fd.setFileSelectionMode(1);
/* 310 */     fd.setAcceptAllFileFilterUsed(false);
/*     */ 
/* 313 */     fd.showOpenDialog(null);
/* 314 */     File f = fd.getSelectedFile();
/*     */ 
/* 316 */     return f.getPath();
/*     */   }
/*     */ 
/*     */   public void setID(String a)
/*     */   {
/* 324 */     this.SID = a;
/*     */   }
/*     */ 
/*     */   public void setType(String a) {
/* 328 */     this.SType = a;
/*     */   }
/*     */ 
/*     */   public void setDesign(String a) {
/* 332 */     this.SDesign = a;
/*     */   }
/*     */ 
/*     */   public void setPad(String a) {
/* 336 */     this.SPad = a;
/*     */   }
/*     */ 
/*     */   public void setPass(String a) {
/* 340 */     this.SPass = a;
/*     */   }
/*     */ 
/*     */   public void setStatus(String a) {
/* 344 */     this.Status = a;
/*     */   }
/*     */ 
/*     */   public String getSavePath() {
/* 348 */     return this.SavePath;
/*     */   }
/*     */ 
/*     */   public String getMonitorPath() {
/* 352 */     return this.MonitorAddress.getText();
/*     */   }
/*     */ 
/*     */   public void UpdateDisplay() {
/* 356 */     Font font = new Font("TimesRoman", 1, 20);
/*     */ 
/* 358 */     this.IDText.setText(this.SID);
/* 359 */     this.DesignText.setText(this.SDesign);
/* 360 */     this.PadText.setText(this.SPad);
/* 361 */     this.TypeText.setText(this.SType);
/* 362 */     this.jTextArea.setText(this.Status);
/* 363 */     this.Pass.setText(this.SPass);
/* 364 */     if (this.SPass == "Pass")
/* 365 */       this.Pass.setForeground(Color.GREEN);
/*     */     else
/* 367 */       this.Pass.setForeground(Color.RED);
/*     */   }
/*     */ }
