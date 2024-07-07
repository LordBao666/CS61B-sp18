package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH=50;
    private static final int HEIGHT=50;

    private static final Random RANDOM = new Random();
    private static class Position{
        public int posX;
        public int posY;

        public Position(){
            this(0,0);
        }


        public Position(int posX, int posY) {
            this.posX = posX;
            this.posY = posY;
        }
    }

    /**
     * Take the drawing below as an example
     *    ○○○
     *   ○○○○○
     *  ○○○○○○○
     *  ○○○○○○○
     *   ○○○○○
     *    ♦○○
     * Its size is 3.
     * ♦ here means a special ○, and the position of  ♦ is exactly Position p.
     * The addHexagon method will draw a hexagon in terms of  Position p ,int size
     * and TETile t in the TETile [][] world
     */
    private static void addHexagon(TETile [][] world,Position p , int size, TETile t){
        int x = p.posX;
        int y = p.posY;

        //draw the bottom part
        for(int i=0;i<size;i++){
            //draw a row
            int numToDraw = size + 2*i;
            for(int j=0;j<numToDraw;j++){
                //if the indexes of x+j and y are safe
                if( (0<=(x+j) && (x+j)< WIDTH)  && (0<=y && y<HEIGHT) ){
                    world[x+j][y]=t;
                }
            }
            x--;
            y++;
        }

        //draw the upper part
        x=x+1;
        for(int i=0;i<size;i++){
            //draw a row
            int numToDraw = size+ 2*(size-1) - 2*i;
            for (int j = 0; j < numToDraw; j++) {
                if( (0<=(x+j) && (x+j)< WIDTH)  && (0<=y && y<HEIGHT) ){
                    world[x+j][y]=t;
                }
            }
            x++;
            y++;
        }
    }

    /**
     *  the start point of a hexagon is p, return the start position of its
     *  bottomNeighbor.
     *  the @param size is the size of a hexagon
     */
    private static Position bottomNeighbor(Position p , int size){
        return  new Position(p.posX, p.posY-2*size);
    }

    /**
     *  the start point of a hexagon is p, return the start position of its
     *  rightUpperNeighbor.
     *  the @param size is the size of a hexagon
     */
    private static Position rightUpperNeighbor(Position p , int size){
        return  new Position(p.posX+2*size-1, p.posY+size);
    }

    /**
     *  the start point of a hexagon is p, return the start position of its
     *  rightBottomNeighbor.
     *  the @param size is the size of a hexagon
     */
    private static Position rightBottomNeighbor(Position p , int size){
        return  new Position(p.posX+2*size-1, p.posY-size);
    }

    private static TETile randomTile(){
        int r = RANDOM.nextInt(11);
        switch (r){
            case 0:return Tileset.PLAYER;
            case 1:return Tileset.WALL;
            case 2:return Tileset.FLOOR;
            case 3:return Tileset.GRASS;
            case 4:return Tileset.WATER;
            case 5:return Tileset.FLOWER;
            case 6:return Tileset.LOCKED_DOOR;
            case 7:return Tileset.UNLOCKED_DOOR;
            case 8:return Tileset.SAND;
            case 9:return Tileset.MOUNTAIN;
            case 10:return Tileset.TREE;
            default:return Tileset.TREE;
        }
    }

    /**
     *      88
     *     8888
     *   ?x888811
     *  xxxx881111
     *  xxxxoo1111
     *   xxoooo11
     *     oooo
     *      oo
     * ? is a special x,and it is startPoint, size=2, sizeofHexWorld=2
     * @param startPoint the start point for hexWorld
     * @param sizeofHexWorld  take a hexagon as a basic unit,  then sizeofHexWorld means the number of each edge.
     * @param size  size of a hexagon
     */
    private static void buildHexWorld(Position startPoint, TETile [][] world, int sizeofHexWorld,int size){
        Position p = startPoint;
        //draw the left part
        for(int i=0;i<sizeofHexWorld;i++){
            Position q=p;
            for(int j=0;j<sizeofHexWorld+i;j++){
                addHexagon(world,p,size,randomTile());
                p=bottomNeighbor(p,size);
            }
            p=rightUpperNeighbor(q,size);
        }

        //draw the right part
        p=bottomNeighbor(p,size);
        for(int i=0;i<sizeofHexWorld-1;i++){
            Position q=p;
            for(int j=0;j<sizeofHexWorld+sizeofHexWorld-2-i;j++){
                addHexagon(world,p,size,randomTile());
                p=bottomNeighbor(p,size);
            }
            p=rightBottomNeighbor(q,size);
        }
    }

    public static void main(String[] args) {
        TERenderer tr = new TERenderer();
        tr.initialize(WIDTH,HEIGHT);

        //initialize word
        TETile [][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        int size=3;
        int sizeOfHexWorld=3;
        buildHexWorld(new Position(size,HEIGHT/2 +  (sizeOfHexWorld/2) * size),world,sizeOfHexWorld,size);


        tr.renderFrame(world);
    }
}
