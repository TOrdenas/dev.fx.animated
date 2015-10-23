
/**
* usage 
    public void run(Button button,Task t){
        Animated a=new Animated();
        t.setOnSucceeded(ev -> a.stop(button));
        new Thread(t).start();
        a.start(button);
    }
*/

private class Animated extends ColorAdjust{

        private Effect e=null;

        public Animated(){
            super();

            setBrightness(0.0);
        }

        public void start(Node node){
            this.e=node.getEffect();
            node.setEffect(this);
            in();
        }
        public void stop(Node node){
            node.setEffect(this.e);
        }

        private void in(){
            Timeline tm = new Timeline(
                    new KeyFrame(Duration.seconds(0),new KeyValue(brightnessProperty(), brightnessProperty().getValue(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(1), new KeyValue(brightnessProperty(), -1, Interpolator.LINEAR))
            );
            tm.setCycleCount(1);
            tm.setAutoReverse(false);

            tm.setOnFinished((e)->out());

            tm.play();
        }
        private void out(){
            Timeline tm = new Timeline(
                    new KeyFrame(Duration.seconds(0),new KeyValue(brightnessProperty(), brightnessProperty().getValue(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(1), new KeyValue(brightnessProperty(), 0, Interpolator.LINEAR))
            );
            tm.setCycleCount(1);
            tm.setAutoReverse(false);

            tm.setOnFinished((e)->in());

            tm.play();
        }
    }
