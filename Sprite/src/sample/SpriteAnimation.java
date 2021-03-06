package sample;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;


public class SpriteAnimation extends Transition {

/*
    private  final Image IMAGE = new Image(getClass().getResourceAsStream("1.png"));
    private static final int COLUMNS  =   3;
    private static final int COUNT    =  3;
    private static final int OFFSET_X =  0;
    private static final int OFFSET_Y =  32;
    private static final int WIDTH    = 32;
    private static final int HEIGHT   = 32;
*/



        private final ImageView imageView;
        private final int count;
        private final int columns;
        private int offsetX;
        private int offsetY;
        private final int width;
        private final int height;

        public SpriteAnimation(
                ImageView imageView,
                Duration duration,
                int count, int columns,
                int offsetX, int offsetY,
                int width, int height) {
            this.imageView = imageView;
            this.count = count;
            this.columns = columns;
            this.offsetX = offsetX;
            this.offsetY = offsetY;
            this.width = width;
            this.height = height;
            setCycleDuration(duration);
            setCycleCount(Animation.INDEFINITE);
            setInterpolator(Interpolator.LINEAR);
            this.imageView.setViewport(new Rectangle2D(offsetX,offsetY,width,height));
        }
        public void setOffsetX(int x){
            this.offsetX=x;
        }

        public void setOffsetY(int y) { this.offsetY=y; }
        protected void interpolate(double k) {
            final int index = Math.min((int) Math.floor(k * count), count - 1);
            final int x = (index % columns) * width + offsetX;
            final int y = (index / columns) * height + offsetY;
            imageView.setViewport(new Rectangle2D(x, y, width, height));
        }
    }

