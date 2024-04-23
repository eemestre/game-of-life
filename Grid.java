import java.awt.Color;

class Grid {
    boolean[][] grid;
    boolean[][] temp;
    int x, y;
    int size;

    public Grid(int x, int y, int size) {
        this.grid = new boolean[x][y];
        this.temp = new boolean[x][y];
        this.x = x;
        this.y = y;
        this.size = size;

        for(int i = 0; i < x; i++) {
            for(int j = 0; j < y; j++) {
                this.grid[i][j] = false;
                this.temp[i][j] = false;
            }
        }
    }

    public void reset() {
        for(int i = 0; i < this.x; i++) {
            for(int j = 0; j < this.y; j++) {
                this.grid[i][j] = false;
                this.temp[i][j] = false;
            }
        }
    }

    public void tick() {
        int liveNeighbors = 0;
        int controle = 0;

        for(int i = 0; i < this.x; i++) {
            for(int j = 0; j < this.y; j++) {
                liveNeighbors = 0;

                for(int m = -1; m <= 1; m++) {
                    for(int n = -1; n <= 1; n++) {
                        if(i + m >= 0 && j + n >= 0 && i + m < this.x && j + n < this.y) {
                            if(m == 0 && n == 0) {
                                ;
                            } else {
                                if(this.grid[i+m][j+n]) {
                                    controle += 1;
                                    liveNeighbors += 1;
                                }
                            }
                        }

                    }
                }

                if(this.grid[i][j]) {
                    if(liveNeighbors <= 1) {
                        this.temp[i][j] = false;
                    } else if(liveNeighbors >= 4) {
                        this.temp[i][j] = false;
                    } else {
                        this.temp[i][j] = true;
                    }
                } else {
                    if(liveNeighbors == 3) {
                        this.temp[i][j] = true;
                    } else {
                        this.temp[i][j] = false;
                    }
                }
            }
        }
        for(int i = 0; i < this.x; i++) {
            this.grid[i] = this.temp[i].clone();
        }
    }

    public void render() {
        for(int i = 0; i < this.x; i++) {
            for(int j = 0; j < this.y; j++) {
                if(this.grid[i][j]) {
                    Main.g.setColor(Color.white);
                } else {
                    Main.g.setColor(Color.black);
                }
                Main.g.fillRect(i*this.size, j*this.size, this.size, this.size);
            }
        }
    }
}