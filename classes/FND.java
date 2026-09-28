class FND{
    int[] input = new int[7];
    char[][] seg = new char[5][5];
    char ch;
    
    FND(){
        ch = '#';
        reset();
    }
    
    FND(char c){
        ch = c;
        reset();
    }
    
    FND(char c, int[] in){
        ch = c;
        input = in;
        reset();
        cnvt();
    }
    
    private void reset(){
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++)
                seg[i][j] = ' ';
        }
    }
    
    private void cnvt(){
        reset();
        
        if(input[0] == 1) for(int i=0; i<5; i++) seg[0][i] = ch;    //a
        if(input[1] == 1) for(int i=0; i<3; i++) seg[i][4] = ch;    //b
        if(input[2] == 1) for(int i=0; i<3; i++) seg[i+2][4] = ch;  //c 
        if(input[3] == 1) for(int i=0; i<5; i++) seg[4][i] = ch;    //d
        if(input[4] == 1) for(int i=0; i<3; i++) seg[i+2][0] = ch;  //e
        if(input[5] == 1) for(int i=0; i<3; i++) seg[i][0] = ch;    //f
        if(input[6] == 1) for(int i=0; i<5; i++) seg[2][i] = ch;    //g
    }
    
    void setChar(char c){
        ch = c;
    }
    
    void setInput(int[] in){
        input = in;
        cnvt();
    }
    
    void dispFnd(){
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                System.out.print(seg[i][j]);
            }
            System.out.println("");
        }
        
        System.out.println("");
    }
    
    void dispFnd(int line){
        for(int j=0; j<5; j++){
            System.out.print(seg[line][j]);
        }
        System.out.print(" ");
    }
}
