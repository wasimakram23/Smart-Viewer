import java.awt.FileDialog;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorConvertOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class SmartViewerFrame extends javax.swing.JFrame {
	BufferedImage cb[]=new BufferedImage[110];
	static BufferedImage image;
	String filename;
	int count, numb;
	File[] listOfFiles;
	File files;
	int height, width, newW, newH, x, y, iw, ih, w, h;
	double xScale, yScale, scale;
	JPanel l;
	
	static int brisv=0,shsv=0,blsv=0;

    
    public SmartViewerFrame() {
        initComponents();
    }
    void display() throws IOException {

		l = new JPanel() {

			public void paintComponent(Graphics g) {

				super.paintComponent(g);

				Graphics2D g2 = (Graphics2D) g;

				w = getWidth();
				h = getHeight();
				iw = image.getWidth();
				ih = image.getHeight();
				xScale = (double) w / iw;
				yScale = (double) h / ih;
				scale = Math.min(xScale, yScale); // scale to fit

				width = (int) (scale * iw);
				height = (int) (scale * ih);
				x = (w - width) / 2;
				y = (h - height) / 2;
				g2.drawImage(image, x, y, width, height, this);

			}
		};

		l.setOpaque(false);
		l.setBounds(10, 10, 100, 100);
		add(l);
		l.setLocation(250,0);

		l.setSize(800, 700);

	}


    
    @SuppressWarnings("unchecked")
    private void initComponents() {

        editpanel = new javax.swing.JPanel();
        cl = new javax.swing.JLabel();
        bl = new javax.swing.JLabel();
        sl = new javax.swing.JLabel();
        bll = new javax.swing.JLabel();
        cos = new javax.swing.JButton();
        bris = new javax.swing.JSlider(SwingConstants.HORIZONTAL,0,200,5);
        shs = new javax.swing.JSlider(SwingConstants.HORIZONTAL,0,200,5);
        bls = new javax.swing.JSlider(SwingConstants.HORIZONTAL,0,200,5);
        mpanel = new javax.swing.JPanel();
        ml = new javax.swing.JLabel();
        imbuttonpanel = new javax.swing.JPanel();
        pr = new javax.swing.JButton();
        op = new javax.swing.JButton();
        ne = new javax.swing.JButton();
        rl = new javax.swing.JButton();
        rr = new javax.swing.JButton();
        menu = new javax.swing.JMenuBar();
        file = new javax.swing.JMenu();
        open = new javax.swing.JMenuItem();
        save = new javax.swing.JMenuItem();
        saveas = new javax.swing.JMenuItem();
        exit = new javax.swing.JMenuItem();
        features = new javax.swing.JMenu();
        crop = new javax.swing.JMenuItem();
        sketch = new javax.swing.JMenuItem();
        help = new javax.swing.JMenu();
        about = new javax.swing.JMenuItem();

        

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1366, 740));
        
        cl.setText("Grayscale");
        cos.setToolTipText("GRAYSCALE");
        cos.setIcon(new javax.swing.ImageIcon(getClass().getResource("open2.jpg")));
        cos.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("open.jpg")));
        cos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cosActionPerformed(evt);
            }
        });
        

        bl.setText("Brightness");
        bris.setPaintTicks(true);
        bris.setMajorTickSpacing(5);
        bris.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent ce) {
				brischangeperformed(ce);}});

        sl.setText("Posterise");
        shs.setPaintTicks(true);
        shs.setMajorTickSpacing(5);
        shs.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent ce) {
				shschangeperformed(ce);}});

        bll.setText("Blur");
        bls.setPaintTicks(true);
        bls.setMajorTickSpacing(5);
        bls.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent ce) {
				blschangeperformed(ce);}});


        javax.swing.GroupLayout editpanelLayout = new javax.swing.GroupLayout(editpanel);
        editpanel.setLayout(editpanelLayout);
        editpanelLayout.setHorizontalGroup(
            editpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editpanelLayout.createSequentialGroup()
                        .addComponent(cl)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(cos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(editpanelLayout.createSequentialGroup()
                        .addComponent(bll, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bls, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(editpanelLayout.createSequentialGroup()
                        .addComponent(bl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bris, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(editpanelLayout.createSequentialGroup()
                        .addComponent(sl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(shs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        editpanelLayout.setVerticalGroup(
            editpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editpanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(editpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editpanelLayout.createSequentialGroup()
                        .addGroup(editpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cl, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addComponent(bl, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(bris, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(editpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sl, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(shs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(editpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bll, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bls, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pr.setToolTipText("Previous");
        pr.setIcon(new javax.swing.ImageIcon(getClass().getResource("previous.jpg")));
        pr.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("previous.jpg")));
        pr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prActionPerformed(evt);
            }
        });

        op.setToolTipText("Open");
        op.setIcon(new javax.swing.ImageIcon(getClass().getResource("open2.jpg")));
        op.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("open2.jpg")));
        op.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opActionPerformed(evt);
            }
        });

        ne.setToolTipText("Next");
        ne.setIcon(new javax.swing.ImageIcon(getClass().getResource("next.jpg")));
        ne.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("next.jpg")));
        ne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                neActionPerformed(evt);
            }
        });

        rl.setToolTipText("Rotate Left");
        rl.setIcon(new javax.swing.ImageIcon(getClass().getResource("rleft.jpg")));
        rl.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("rleft.jpg")));
        rl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rlActionPerformed(evt);
            }
        });

        rr.setToolTipText("Rotate Right");
        rr.setIcon(new javax.swing.ImageIcon(getClass().getResource("rright.jpg")));
        rr.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("rright.jpg")));
        rr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rrActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout imbuttonpanelLayout = new javax.swing.GroupLayout(imbuttonpanel);
        imbuttonpanel.setLayout(imbuttonpanelLayout);
        imbuttonpanelLayout.setHorizontalGroup(
            imbuttonpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imbuttonpanelLayout.createSequentialGroup()
                .addGap(274, 274, 274).addComponent(rl, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pr, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(op, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ne, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rr, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 381, Short.MAX_VALUE)
              .addContainerGap())
        );
        imbuttonpanelLayout.setVerticalGroup(
            imbuttonpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imbuttonpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(imbuttonpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, imbuttonpanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(imbuttonpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pr)
                            .addComponent(ne)
                            .addComponent(rl)
                            .addComponent(rr)
                            ))
                    .addComponent(op, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        file.setText("File");

        open.setText("Open");
        open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openActionPerformed(evt);
            }
        });
        file.add(open);

        save.setText("Save");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        file.add(save);

        saveas.setText("Save As");
        saveas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveasActionPerformed(evt);
            }
        });
        file.add(saveas);

        exit.setText("Exit");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        file.add(exit);

        menu.add(file);

        features.setText("Features");

        crop.setText("Crop");
        crop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cropActionPerformed(evt);
            }
        });
        features.add(crop);

        sketch.setText("Sketch");
        sketch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sketchActionPerformed(evt);
            }
        });
        features.add(sketch);

        menu.add(features);

        help.setText("Help");

        about.setText("About");
        about.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutActionPerformed(evt);
            }
        });
        help.add(about);

        menu.add(help);

        setJMenuBar(menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imbuttonpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(editpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(editpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imbuttonpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );


        pack();
    }
    
    private void cosActionPerformed(ActionEvent evt) {
		ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);  
		ColorConvertOp op = new ColorConvertOp(cs, null);  
		 image = op.filter(image, null); 
		 try {
				display();
			} catch (IOException ex) {
				Logger.getLogger(SmartViewerFrame.class.getName()).log(Level.SEVERE,
						null, ex);
			}
	}
	
    protected void blschangeperformed(ChangeEvent ce) {
    	
    	if(bls.getValue()>blsv){
    		if(bls.getValue()%5==0 && bls.getValue()!=0){
    		
    	  float[] blurKernel = { 1 / 9f, 1 / 9f, 1 / 9f, 1 / 9f, 1 / 9f, 1 / 9f,
				1 / 9f, 1 / 9f, 1 / 9f };

		   BufferedImageOp blur = new ConvolveOp(new Kernel(3, 3, blurKernel));
		   image = blur.filter(image, null);
		   cb[bls.getValue()]=image;
		   blsv=bls.getValue();
		try {
			display();
		} catch (IOException ex) {
			Logger.getLogger(SmartViewerFrame.class.getName()).log(Level.SEVERE,
					null, ex);
		}}}
    	
    	else
    	{
    		if(bls.getValue()%10==0 && bls.getValue()!=0){
	        	image=cb[bls.getValue()];
	        	blsv=bls.getValue();
	        	 try {
	        			display();
	        		} catch (IOException ex) {
	        			Logger.getLogger(SmartViewerFrame.class.getName()).log(Level.SEVERE,
	        					null, ex);
	        		}}
    	}
    		
		
	}
	
	protected void brischangeperformed(ChangeEvent ce) {
		  if(bris.getValue()>brisv){
		       if(bris.getValue()%10==0 && bris.getValue()!=0){
		               RescaleOp op = new RescaleOp(1.1f, 5, null);
		               image = op.filter(image, null);
		               cb[bris.getValue()]=image;
		               brisv=bris.getValue();
		              
		               try {
		       			display();
		       		} catch (IOException ex) {
		       			Logger.getLogger(SmartViewerFrame.class.getName()).log(Level.SEVERE,
		       					null, ex);
		       		}
						
					}}
		        else
		        {
		        	if(bris.getValue()%10==0 && bris.getValue()!=0){
		        	image=cb[bris.getValue()];
		        	brisv=bris.getValue();
		        	 try {
		        			display();
		        		} catch (IOException ex) {
		        			Logger.getLogger(SmartViewerFrame.class.getName()).log(Level.SEVERE,
		        					null, ex);
		        		}}
		        }
		        
		
	}
	
	protected void shschangeperformed(ChangeEvent ce) {
		if(shs.getValue()>shsv){
		       if(shs.getValue()%20==0 && shs.getValue()!=0){
		    	   Kernel kernel = new Kernel(3, 3,new float[]{-1, -1, -1,-1, 9, -1,-1, -1, -1});
		    	   BufferedImageOp op = new ConvolveOp(kernel);
		               image = op.filter(image, null);
		               cb[shs.getValue()]=image;
		               shsv=shs.getValue();
		              
		               try {
		       			display();
		       		} catch (IOException ex) {
		       			Logger.getLogger(SmartViewerFrame.class.getName()).log(Level.SEVERE,
		       					null, ex);
		       		}
						
					}}
		        else
		        {
		        	if(shs.getValue()%20==0){
		        		if(shs.getValue()==0)
		        			image=cb[0];
		        		else
		        	        image=cb[shs.getValue()];
		        	shsv=shs.getValue();
		        	 try {
		        			display();
		        		} catch (IOException ex) {
		        			Logger.getLogger(SmartViewerFrame.class.getName()).log(Level.SEVERE,
		        					null, ex);
		        		}}
		        }
		
	}
	
	private void opActionPerformed(java.awt.event.ActionEvent evt) {  
		brisv=0;
		shsv=0;
		blsv=0;
		bris.setValue(5);
		shs.setValue(5);
		bls.setValue(5);
    	 FileDialog fd = new FileDialog(SmartViewerFrame.this, "select File",
 				FileDialog.LOAD);
 		fd.show();
 		if (fd.getFile() != null) {
 			filename = fd.getDirectory() + fd.getFile();

 			setTitle(filename + " - Image Viewer");
 			files = new File(filename);
 			String dir = fd.getDirectory();
 			File Dir_file = new File(dir);
 			 File directory =files.getParentFile();
 			listOfFiles = Dir_file.listFiles();
 			//System.out.println(dir);
 			try {
 				image = ImageIO.read(files);
 				cb[0]=image;

 			} catch (IOException ex) {
 				Logger.getLogger(SmartViewerFrame.class.getName()).log(
 						Level.SEVERE, null, ex);
 			}
 			try {
 				display();
 			} catch (IOException ex) {
 				Logger.getLogger(SmartViewerFrame.class.getName()).log(
 						Level.SEVERE, null, ex);
 			}

 			for (int i = 0; i < listOfFiles.length; i++) {
 				String name = listOfFiles[i].toString();
 				if (name.endsWith("jpg") || name.endsWith("JPG")
 						|| name.endsWith("png") || name.endsWith("PNG")) {

 					//System.out.println("check path" + listOfFiles[i]);
 					if (listOfFiles[i].equals(files)) {
 						//System.out.println("file number is" + i);
 						numb = i;
 					}

 				}
 			}

 		}

 		count = numb;


    }                                                                    

    private void prActionPerformed(java.awt.event.ActionEvent evt) {  
		brisv=0;
		shsv=0;
		blsv=0;
		bris.setValue(5);
		shs.setValue(5);
		bls.setValue(5);
    	int i = listOfFiles.length;
		try {

			if (count == 0)
				count = i;
			count--;
			String name = listOfFiles[count].toString();
			if (name.endsWith("jpg") || name.endsWith("JPG")) {
				files=listOfFiles[count];
				image = ImageIO.read(listOfFiles[count]);
				cb[0]=image;
				setTitle(listOfFiles[count].toString() + " - Smart Viewer");
				filename=listOfFiles[count].toString();
			}
		} catch (IOException ex) {
			Logger.getLogger(SmartViewerFrame.class.getName()).log(Level.SEVERE,
					null, ex);
		}

		try {
			display();
		} catch (IOException ex) {
			Logger.getLogger(SmartViewerFrame.class.getName()).log(Level.SEVERE,
					null, ex);
		}


    }                                  

    private void neActionPerformed(java.awt.event.ActionEvent evt) {   
    	
		brisv=0;
		shsv=0;
		blsv=0;
		bris.setValue(5);
		shs.setValue(5);
		bls.setValue(5);
   	 int i = listOfFiles.length;

   		try {
   			count++;
   			if (count == i) {
   				count = 0;
   			}
   			String name = listOfFiles[count].toString();
   			if (name.endsWith("jpg") || name.endsWith("JPG")) {
   				image = ImageIO.read(listOfFiles[count]);
   				cb[0]=image;
   				setTitle(listOfFiles[count].toString() + " - Smart Viewer");
   			}
   		} catch (IOException ex) {
   			Logger.getLogger(SmartViewerFrame.class.getName()).log(Level.SEVERE,
   					null, ex);
   		}

   		try {
   			display();
   		} catch (IOException ex) {
   			Logger.getLogger(SmartViewerFrame.class.getName()).log(Level.SEVERE,
   					null, ex);
   		}


    }                                  

    private void rlActionPerformed(java.awt.event.ActionEvent evt) {                                   
    	AffineTransform tx = new AffineTransform();
		tx.rotate(Math.toRadians(-90), image.getWidth() / 2,
				image.getHeight() / 2);
		AffineTransformOp op = new AffineTransformOp(tx,
				AffineTransformOp.TYPE_BICUBIC);
		image = op.filter(image, null); 
		try {
			display();
		} catch (IOException ex) {
			Logger.getLogger(SmartViewerFrame.class.getName()).log(Level.SEVERE,
					null, ex);
		}
    }                                  

    private void rrActionPerformed(java.awt.event.ActionEvent evt) {                                   
    	AffineTransform tx = new AffineTransform();
		tx.rotate(Math.toRadians(90), image.getWidth() / 2,
				image.getHeight() / 2);

		AffineTransformOp op = new AffineTransformOp(tx,
				AffineTransformOp.TYPE_BILINEAR);
		image = op.filter(image, null);
		try {
			display();
		} catch (IOException ex) {
			Logger.getLogger(SmartViewerFrame.class.getName()).log(Level.SEVERE,
					null, ex);
		}

    }                                  

    private void openActionPerformed(java.awt.event.ActionEvent evt) {                                     
    	
		brisv=0;
		shsv=0;
		blsv=0;
		bris.setValue(5);
		shs.setValue(5);
		bls.setValue(5);
    	 FileDialog fd = new FileDialog(SmartViewerFrame.this, "select File",
 				FileDialog.LOAD);
 		fd.show();
 		if (fd.getFile() != null) {
 			filename = fd.getDirectory() + fd.getFile();

 			setTitle(filename + " - Image Viewer");
 			files = new File(filename);
 			String dir = fd.getDirectory();
 			File Dir_file = new File(dir);
 			 File directory =files.getParentFile();
 			listOfFiles = Dir_file.listFiles();
 			System.out.println(dir);
 			try {
 				image = ImageIO.read(files);
 				cb[0]=image;

 			} catch (IOException ex) {
 				Logger.getLogger(SmartViewerFrame.class.getName()).log(
 						Level.SEVERE, null, ex);
 			}
 			try {
 				display();
 			} catch (IOException ex) {
 				Logger.getLogger(SmartViewerFrame.class.getName()).log(
 						Level.SEVERE, null, ex);
 			}

 			for (int i = 0; i < listOfFiles.length; i++) {
 				String name = listOfFiles[i].toString();
 				if (name.endsWith("jpg") || name.endsWith("JPG")
 						|| name.endsWith("png") || name.endsWith("PNG")) {

 					//System.out.println("check path" + listOfFiles[i]);
 					if (listOfFiles[i].equals(files)) {
 						//System.out.println("file number is" + i);
 						numb = i;
 					}

 				}
 			}

 		}

 		count = numb;


    }                                    

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {                                     

			BufferedImage saveimage = image;
			File outputfile = new File(filename);

			try {
				
				ImageIO.write(saveimage, "JPG", outputfile);
			} catch (IOException ex) {
				Logger.getLogger(SmartViewerFrame.class.getName()).log(
						Level.SEVERE, null, ex);
			}
		
    }                                    

    private void saveasActionPerformed(java.awt.event.ActionEvent evt) {                                       
    	FileDialog sv = new FileDialog(SmartViewerFrame.this, "", FileDialog.SAVE);
		sv.show();

	
		int i;
		if (sv.getFile() != null) {
			filename = sv.getDirectory() + sv.getFile();
			setTitle(filename);

			BufferedImage saveimage = image;
			File outputfile = new File(filename + ".jpg");

			try {
				
				ImageIO.write(saveimage, "JPG", outputfile);
			} catch (IOException ex) {
				Logger.getLogger(SmartViewerFrame.class.getName()).log(
						Level.SEVERE, null, ex);
			}
		}
    }

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {                                     
    	System.exit(0);
    }                                    

    private void cropActionPerformed(java.awt.event.ActionEvent evt) {                                     
        JOptionPane.showMessageDialog(null,"This feature will be add in future update .", "WARNING", JOptionPane.WARNING_MESSAGE);
    }                                    

    private void sketchActionPerformed(java.awt.event.ActionEvent evt) {                                       
    	JOptionPane.showMessageDialog(null,"This feature will be add in future update .", "WARNING", JOptionPane.WARNING_MESSAGE);
    	   
    }                                      

    private void aboutActionPerformed(java.awt.event.ActionEvent evt) {                                      
    	JOptionPane.showMessageDialog(null,"All copyright of this software is reserved by\n      MAWSK DEVELOPERS CO.", "ABOUT", JOptionPane.INFORMATION_MESSAGE);
    	   
    } 
 
                        

   
    private javax.swing.JMenuItem about;
    private javax.swing.JLabel bl;
    private javax.swing.JLabel bll;
    private javax.swing.JSlider bls;
    private javax.swing.JSlider bris;
    private javax.swing.JLabel cl;
    private javax.swing.JButton cos;
    private javax.swing.JMenuItem crop;
    private javax.swing.JPanel editpanel;
    private javax.swing.JMenuItem exit;
    private javax.swing.JMenu features;
    private javax.swing.JMenu file;
    private javax.swing.JMenu help;
    private javax.swing.JPanel imbuttonpanel;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuBar menu;
    private javax.swing.JLabel ml;
    private javax.swing.JPanel mpanel;
    private javax.swing.JButton ne;
    private javax.swing.JButton op;
    private javax.swing.JMenuItem open;
    private javax.swing.JButton pr;
    private javax.swing.JButton rl;
    private javax.swing.JButton rr;
    private javax.swing.JMenuItem save;
    private javax.swing.JMenuItem saveas;
    private javax.swing.JSlider shs;
    private javax.swing.JMenuItem sketch;
    private javax.swing.JLabel sl;
              
}
