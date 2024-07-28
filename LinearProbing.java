public class LinearProbing<Key> {
    Key[] table;
    int M;  //size
    int N; // number of full elements

    public LinearProbing(int M) {
        table = (Key[]) new Object[M];
        this.M = M;
    }

    public int hash(Key t) {
        return ((t.hashCode() & 0x7fffffff) % M);
    }

    public boolean insert(Key key) {
        int i;
        int h = hash(key);
        System.out.print(" hash: " + h);
        for (i = h; table[i] != null; i = (i + 1) % M) {
            if (table[i].equals(key)) {
                return true;
            }
            if (i + 1 == h) {
                return false; // table is full
            }
        }
        table[i] = key;
        N++; // increase number of stored items
        return true;
    }

    public boolean contains(Key key) {
        int ix = hash(key);
        System.out.print(" hash : " + ix);
        int startIx = ix;

        while (table[ix] != null && (ix + 1 != startIx)) {
            if (table[ix].equals(key)) {
                return true;
            }
            ix = (ix + 1) % M;

        }
        return false;
    }
    public int getIndex(Key key){
        int index=hash(key);
        for (int i=0;i<M;i++){
            if (table[index].equals(key)){
                return index;
            }
            if (index+1==M)
                index=0;
            else
                index++;
        }
        return -1;
    }
    public Key getData(int number){
        return table[number];
    }

}
