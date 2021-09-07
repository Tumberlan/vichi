public class LInear {

    public double function(int a, int b, int c, double x){
        return (x*x*x + a*x*x + b*x + c);
    }

    public double Discr(int a, int b){

        int oldest = 3;

        double exc = 4*a*a - 4*oldest*b;

        System.out.println("Discr is: " + exc);
        return exc;
    }


    public Answer countAnswers(double discr, int a){
        double plus_ans = (-2*a + Math.sqrt(discr))/6;
        double minus_ans = (-2*a - Math.sqrt(discr))/6;
        return new Answer(plus_ans,minus_ans);
    }


    public double inBorder(double right_border, double left_border, int a, int b, int c){
        double epsilon = 0.01;
        double new_border = left_border;
        System.out.println("Borders: " + left_border + " " + right_border);
        System.out.println("Difference: " + Math.abs(function(a, b, c,right_border)-function(a,b,c,left_border)));
        while (Math.abs(function(a, b, c,right_border)-function(a,b,c,left_border)) > epsilon){
            new_border = left_border + Math.abs(right_border-left_border)/2;
            System.out.println("New border: " + new_border);
            if(function(a, b, c,left_border)*function(a,b,c,new_border) < 0){
                left_border = new_border;
            }else{
                right_border = new_border;
            }
            System.out.println("Borders: " + left_border + " " + right_border);
            System.out.println("Difference: " + Math.abs(function(a, b, c,right_border)-function(a,b,c,left_border)));
        }
        return new_border;
    }



    public boolean checkBorder(double border, int a, int b, int c){
        if (function(a, b, c,border) == 0){
            return true;
        }
        return false;
    }

    public double changeBorder(double right_border, double left_border, boolean direction_right, int a, int b, int c){

        System.out.println( "Left border: " + left_border + "; Right border: " + right_border);
        if(direction_right){
            System.out.println("Right way");
            System.out.println("F Left: " + function(a, b, c,left_border) + "; F Right: " + function(a,b,c,right_border));
            while(function(a, b, c,left_border)*function(a,b,c,right_border) >= 0){
                if(checkBorder(left_border,a,b,c)){
                    return left_border;
                }
                if(checkBorder(right_border,a,b,c)){
                    return right_border;
                }
                left_border = right_border;
                right_border++;
                System.out.println("F Left: " + function(a, b, c,left_border) + "; F Right: " + function(a,b,c,right_border));
                System.out.println( "Left border: " + left_border + "; Right border: " + right_border);
            }
        }else{
            System.out.println("Left way");
            System.out.println("F Left: " + function(a, b, c,left_border) + "; F Right: " + function(a,b,c,right_border));
            while(function(a, b, c,left_border)*function(a,b,c,right_border) >= 0){
                if(checkBorder(left_border,a,b,c)){
                    return left_border;
                }
                if(checkBorder(right_border,a,b,c)){
                    return right_border;
                }
                right_border = left_border;
                left_border--;
                System.out.println("F Left: " + function(a, b, c,left_border) + "; F Right: " + function(a,b,c,right_border));
                System.out.println( "Left border: " + left_border + "; Right border: " + right_border);
            }
        }
        return inBorder(right_border, left_border, a, b, c);
    }


    public double moveBorder(double x, boolean directoin_right, int a, int b, int c){
        double left_border, right_border;
        if(directoin_right){
            right_border = x+1;
            left_border = x;
        }else {
            right_border = x;
            left_border = x-1;
        }
        return changeBorder(right_border, left_border, directoin_right, a, b, c);
    }



    public void giveAnswer(Answer disAnswer, int a, int b, int c){
        System.out.println("F1: " + function(a, b, c, disAnswer.with_minus) + "; F2: " + function(a, b, c, disAnswer.with_plus));
        if(function(a, b, c, disAnswer.with_minus) > 0 && function(a, b, c, disAnswer.with_plus) > 0){
            System.out.println("Answer is: x1 = " + moveBorder(disAnswer.with_minus,false,a,b,c) + "; x2 and x3 are complex");
        }else if(function(a, b, c, disAnswer.with_minus) < 0 && function(a, b, c, disAnswer.with_plus) < 0){
            System.out.println("Answer is: x1 = " + moveBorder(disAnswer.with_plus,true,a,b,c) + " x2 and x3 are complex");
        }else if(function(a, b, c, disAnswer.with_minus) > 0 && function(a, b, c, disAnswer.with_plus) == 0){
            System.out.println("Answer is: x1 = " + moveBorder(disAnswer.with_minus,false,a,b,c) + "; x2 and x3 = " + disAnswer.with_plus);
        }else if(function(a, b, c, disAnswer.with_minus) == 0 && function(a, b, c, disAnswer.with_plus) < 0){
            System.out.println("Answer is: x1 = " + moveBorder(disAnswer.with_plus,true,a,b,c) + "; x2 and x3 = " + disAnswer.with_minus);
        }else if(function(a, b, c, disAnswer.with_minus) > 0 && function(a, b, c, disAnswer.with_plus) < 0){
            System.out.println("Answer is: x1 = " + moveBorder(disAnswer.with_minus,false,a,b,c) + "; x2 = " +
                    inBorder(disAnswer.with_plus, disAnswer.with_minus,a,b,c) + "; x3 = " + moveBorder(disAnswer.with_plus,true,a,b,c));
        }
    }

    public void allOperations(int a, int b, int c){
        double discr = Discr(a,b);
        if(discr < 0){
            System.out.println("Answer is: x1 = " + moveBorder(0,false,a,b,c) + "; x2 and x3 are complex");
        }else{
            Answer disAnswer = countAnswers(discr, a);
            System.out.println("First root: " + disAnswer.with_minus + "; Second root: " + disAnswer.with_plus);
            giveAnswer(disAnswer,a,b,c);
        }
    }


}
