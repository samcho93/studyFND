public class Main
{
    static int cnt;
    static int oldclk;
    
    static void dispfnd(int[] fnd){
        char[][] seg = new char[5][5];
        
        for(int i=0; i<5; i++)
            for(int j=0; j<5; j++)
                seg[i][j] = ' ';
                
        if(fnd[0] == 1) for(int i=0; i<5; i++) seg[0][i] = '#';
        if(fnd[1] == 1) for(int i=0; i<3; i++) seg[i][4] = '#';
        if(fnd[2] == 1) for(int i=0; i<3; i++) seg[i+2][4] = '#';
        if(fnd[3] == 1) for(int i=0; i<5; i++) seg[4][i] = '#';
        if(fnd[4] == 1) for(int i=0; i<3; i++) seg[i+2][0] = '#';
        if(fnd[5] == 1) for(int i=0; i<3; i++) seg[i][0] = '#';
        if(fnd[6] == 1) for(int i=0; i<5; i++) seg[2][i] = '#';
        
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                System.out.print(seg[i][j]);
            }
            System.out.println("");
        }
    }
    
    static int[] fnd(int[] bin){
        int num;
        int[][] table = {
            {1,1,1,1,1,1,0},
            {0,1,1,0,0,0,0},
            {1,1,0,1,1,0,1},
            {1,1,1,1,0,0,1},
            {0,1,1,0,0,1,1},
            {1,0,1,1,0,1,1},
            {1,0,1,1,1,1,1},
            {1,1,1,0,0,1,0},
            {1,1,1,1,1,1,1},
            {1,1,1,1,0,1,1}
        };
        
        num = bin[3] * 8 + bin[2] * 4 + bin[1] * 2 + bin[0];
        
        return table[num];
    }
    
    static int[] cnvtDec2Bin(int num){
        int[] out = new int[4];
        
        for(int i=0; i<4; i++){
            out[i] = num % 2;
            num = num / 2;
        }
        
        return out;
    }
    
    static int[] cnvtDec2BinBit(int num){
        int[] out = new int[4];
        
        for(int i=0; i<4; i++){
            out[i] = (num & 0x01);
            num >>= 1;
        }
        
        return out;
    }
    
    static int[] ttl7490(int clk, int R0, int R1, int R2){
        if(R0 == 1)  {
            oldclk = 0;
            cnt = 0;
        }
        else if(R1 == 1){
            oldclk = 0;
            cnt = 1;
        }
        else if(R2 == 1){
            oldclk = 0;
            cnt = 2;
        }
        else{
            if(clk == 0 && oldclk == 1){
                if(++cnt == 10) cnt = 0;
            }

            oldclk = clk;
        }
        
        return cnvtDec2Bin(cnt);
    }
    
	public static void main(String[] args) {
		int[] bin = new int[4];
		int[] fnddata = new int[7];
		int[] c = new int[4];
		
		boolean apm = false; // am = false, pm = true
		boolean chk = false;
		
		TTL7490 ttl7490_S1 = new TTL7490();
		TTL7490 ttl7490_S10 = new TTL7490();
		TTL7490 ttl7490_M1 = new TTL7490();
		TTL7490 ttl7490_M10 = new TTL7490();
		TTL7490 ttl7490_H1 = new TTL7490(0,0,1);
		TTL7490 ttl7490_H10 = new TTL7490(0,1,0);
		
		TTL7446 ttl7446_S1 = new TTL7446();
		TTL7446 ttl7446_S10 = new TTL7446();
		TTL7446 ttl7446_M1 = new TTL7446();
		TTL7446 ttl7446_M10 = new TTL7446();
		TTL7446 ttl7446_H1 = new TTL7446();
		TTL7446 ttl7446_H10 = new TTL7446();

        FND cfnd_S1 = new FND();
        FND cfnd_S10 = new FND();
        FND cfnd_M1 = new FND();
        FND cfnd_M10 = new FND();
        FND cfnd_H1 = new FND();
        FND cfnd_H10 = new FND();
        
        ttl7490_H10.setNum(1);
        ttl7490_H1.setNum(2);
        
        ttl7490_M10.setNum(5);
        ttl7490_M1.setNum(9);
        
        ttl7490_S10.setNum(3);
        ttl7490_S1.setNum(0);

// TTL7490 Class		
        System.out.println("------------------------------------");
		for(int i=0; i<102; i++){
		    ttl7490_S1.setClock(i%2);
		    ttl7490_S10.setClock(ttl7490_S1.getOutput()[3]);
		    ttl7490_S10.reset((int)(ttl7490_S10.getOutput()[2] & ttl7490_S10.getOutput()[1]), 0, 0);
		    
		    ttl7490_M1.setClock(ttl7490_S10.getOutput()[2]);
		    ttl7490_M10.setClock(ttl7490_M1.getOutput()[3]);
		    ttl7490_M10.reset((int)(ttl7490_M10.getOutput()[2] & ttl7490_M10.getOutput()[1]), 0, 0);
		    
		    ttl7490_H1.setClock(ttl7490_M10.getOutput()[2]);
		    ttl7490_H10.setClock(ttl7490_H1.getOutput()[3]);
		    if((ttl7490_H10.getOutput()[0] & ttl7490_H1.getOutput()[1] & ttl7490_H1.getOutput()[0]) == 1){
    		    ttl7490_H10.reset(1, 0, 0);
    		    ttl7490_H1.reset(0, 1, 0);
		    }
		    
		    if(ttl7490_H10.getOutput()[0] == 1 && ttl7490_H1.getOutput()[0] == 1 && chk == false){
		        chk = true;
		    }
		    else if(ttl7490_H10.getOutput()[0] == 1 && ttl7490_H1.getOutput()[1] == 1 && chk == true ){
		        chk = false;
		        
		        if(apm == false) apm = true;
    		    else apm = false;
		    }
		    
		    if(i%2 == 0){
		        ttl7446_S1.setInput(ttl7490_S1.getOutput());
        		cfnd_S1.setInput(ttl7446_S1.getOutput());
        		
        		ttl7446_S10.setInput(ttl7490_S10.getOutput());
        		cfnd_S10.setInput(ttl7446_S10.getOutput());
        		
        		ttl7446_M1.setInput(ttl7490_M1.getOutput());
        		cfnd_M1.setInput(ttl7446_M1.getOutput());
        		
        		ttl7446_M10.setInput(ttl7490_M10.getOutput());
        		cfnd_M10.setInput(ttl7446_M10.getOutput());
        		
        		ttl7446_H1.setInput(ttl7490_H1.getOutput());
        		cfnd_H1.setInput(ttl7446_H1.getOutput());
        		
        		ttl7446_H10.setInput(ttl7490_H10.getOutput());
        		cfnd_H10.setInput(ttl7446_H10.getOutput());
        		
        		for(int j=0; j<5; j++){
        		    if(j == 1 && apm == false) 
            		    System.out.print("@@ ");
                    else if(j == 3 && apm == true) 
            		    System.out.print("@@ ");            		    
            		else
            		    System.out.print("   ");
        		    
        		    cfnd_H10.dispFnd(j);
            		cfnd_H1.dispFnd(j);
            		if(j == 1 || j == 3) 
            		    System.out.print("@ ");
            		else
            		    System.out.print("  ");
            		    
            		cfnd_M10.dispFnd(j);
            		cfnd_M1.dispFnd(j);
            		if(j == 1 || j == 3) 
            		    System.out.print("@ ");
            		else
            		    System.out.print("  ");
            		cfnd_S10.dispFnd(j);
            		cfnd_S1.dispFnd(j);
            		System.out.println("");
        		}
        		
        		System.out.println("\n");
		    }
		}
	}
}
