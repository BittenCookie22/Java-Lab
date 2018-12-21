package DF;

import DF.Exceptions.*;
import DF.Values.*;

import java.sql.*;
import java.util.*;

public class DataFrameDB extends DataFrame {
    private Class<? extends Value>[] colTypes;
    private String[] colNames;
    private String tableName;
    private String path;
    private String username;
    private String password;

    public static class DataFrameBDBuilder {
        private String tableName;
        private String path;
        private String username;
        private String password;
        private int colCount;
        private ArrayList<String> colNames;
        private ArrayList<Integer> colTypes;
        Connection connection;
        Statement stmt = null;
        ResultSet rs = null;

        public DataFrameDB build() {
            return new DataFrameDB(path, username, password, tableName, colNames.toArray(new String[0]), getColTypes());
        }


        private Class<? extends Value>[] getColTypes() {
            Class<? extends Value>[] types = new Class[colTypes.size()];
            for (int i = 0; i < colTypes.size(); i++) {
                types[i] = getClass(colTypes.get(i));
            }
            return types;

        }

        private Class<? extends Value> getClass(int i) {
            switch (i) {
                case Types.TIMESTAMP:
                case Types.DATE:
                    return DateTimeValue.class;

                case Types.CHAR:
                case Types.NCHAR:
                case Types.NVARCHAR:
                case Types.VARCHAR:
                case Types.LONGNVARCHAR:
                case Types.LONGVARCHAR:
                    return StringValue.class;

                case Types.REAL:
                case Types.NUMERIC:
                case Types.DECIMAL:
                case Types.DOUBLE:
                    return DoubleValue.class;

                case Types.FLOAT:
                    return FloatValue.class;

                case Types.INTEGER:
                case Types.SMALLINT:
                case Types.TINYINT:
                    return IntegerValue.class;

                default:
                    throw new RuntimeException(":( - nieznany typ" + i);

            }
        }

        public void connect() throws SQLException {
//            try {
//                Class.forName("com.mysql.jdbc.Driver");
//
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
            connection = DriverManager.getConnection(path, username, password);
        }

        public DataFrameBDBuilder(String path, String username, String password, String tableName) throws SQLException {
            colTypes = new ArrayList<>();
            colNames = new ArrayList<>();
            this.path = path;
            this.tableName = tableName;
            this.username = username;
            this.password = password;
            connect();
            colCount = getColcount();
           // System.out.println(colCount);


        }

        private int getColcount() throws SQLException {
            stmt = connection.createStatement();
            rs = connection.getMetaData().getColumns(null, null, tableName, null);

            while (rs.next()) {
                colNames.add(rs.getString("COLUMN_NAME"));
                colTypes.add(rs.getInt("DATA_TYPE"));
            }
           // System.out.println(colNames);
            //System.out.println(colTypes);
            return colNames.size();
        }

    }

    Connection connection = null;
    Statement stmt = null;
    ResultSet rs = null;

    public DataFrameDB(String path, String username, String password, String tableName, String[] colNames, Class<? extends Value>[] colTypes) {
        super(colNames.length);
        this.colTypes = colTypes;
        this.colNames = colNames;
        this.tableName = tableName;
        this.path = path;
        this.username = username;
        this.password = password;
        this.ilosc_wierszy = -1;
    }

    private void connect() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(path, username, password);
            }
        } catch (SQLException e) {
            System.out.println("Nie udalo sie połączyc bład w connect()");
        }

    }

    //--------------funkcje  z DataFrame------------------------


    @Override
    public void dodajElement(Value... elementy) throws DifferentAmountOfColumns, IncoherentTypeException {
        try {
            stmt = connection.createStatement();
            String values;
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < elementy.length; i++) {
                s.append("'").append(elementy[i]).append("'");
                if(i<elementy.length-1){
                    s.append(",");
                }
            }
            values=s.toString();
            int added;
            added = stmt.executeUpdate(String.format("INSERT into %s VAlUES(%s)",tableName,values));
           ilosc_wierszy+=added;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("jakis blad w dodajElement"); //todo: zrobic to lepiej
        }

    }

    @Override
    public DataFrame get(String[] cols, boolean copy) {
        try {
            stmt = connection.createStatement();

            rs = stmt.executeQuery(String.format("SELECT %s FROM %s", String.join(",",cols),tableName));
            return DFfromSelect(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("jakis blad w get copy"); //todo: zrobic to lepiej
    }

    @Override
    public DataFrame iloc(int from, int to) {
        try {
            stmt = connection.createStatement();

            rs = stmt.executeQuery(String.format("SELECT * FROM %s limit %d offset %d", tableName,(to+1-from),from));
            return DFfromSelect(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("jakis blad w ilocu"); //todo: zrobic to lepiej
    }

    @Override
    public String[] zwroc_nazwy() {
        return colNames;
    }


    @Override
    public Class<? extends Value>[] zwroc_typy() {
        return colTypes;
    }

    @Override
    public KolumnaDB get(String colName) {

        int index = 0;
        for (int i = 0; i < colNames.length; i++) {
            if (colNames[i].equals(colName)) {
                index = i;
                break;
            }
        }
        KolumnaDB output = (KolumnaDB) kolumny[index];

        if (output == null) {
            output = new KolumnaDB(index);
            kolumny[index] = output;
        }

        return output;

    }


    @Override
    public int size() {
        connect();
        try {
            stmt = connection.createStatement();

            rs = stmt.executeQuery(String.format("SELECT count(*) FROM %s ", tableName));
            rs.next();

            ilosc_wierszy = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ilosc_wierszy;
    }

    @Override
    public Value[] zwrocWiersz(int i) {
        Value row[] = new Value[colNames.length];
        try {
            connect();
            stmt = connection.createStatement();
            rs = stmt.executeQuery(String.format("SELECT * FROM %s limit 1 offset %d ", tableName, i));
            rs.next();
            for (int j = 0; j < colNames.length; j++) {
                String s = rs.getString(colNames[j]);
                try {
                    row[j] = colTypes[j].newInstance().create(s);
                } catch (IllegalAccessException | InstantiationException e) {
                    e.printStackTrace();
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace(); //todo: zrobic to lepiej

        } finally {
            // zwalniamy zasoby, które nie będą potrzebne
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                } // ignore
                rs = null;
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                } // ignore

                stmt = null;
            }
        }

        return row;
    }

    @Override
    public String toString() {
        StringBuilder bufor = new StringBuilder();

        for (int i = 0; i < iloscKolumn(); i++) {
            bufor.append(colNames[i]).append(":").append(colTypes[i].getSimpleName()).append(" || ");
        }

        bufor.append("\n");
        connect();
        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(String.format("SELECT * FROM %s", tableName));

            while (rs.next()) {
                for (int i = 0; i < iloscKolumn(); i++) {
                    bufor.append(rs.getString(colNames[i]));
                    bufor.append(" || ");
                }
                bufor.append("\n");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bufor.toString();
    }


    //---------------------------KOLUMNA DB-----------------------------
    public class KolumnaDB extends Kolumna {
        private int index;

        public KolumnaDB(int index) {
            super(colNames[index], colTypes[index]);
            this.index = index;
        }

        public Kolumna convertToKolumna() throws SQLException {
            Kolumna output = new Kolumna(getNazwa(), getType());
            stmt = connection.createStatement();
            rs = stmt.executeQuery(String.format("SELECT %s FROM %s", getNazwa(), tableName));

            while (rs.next()) {
                try {
                    output.add(getType().newInstance().create(rs.getString(getNazwa())));
                } catch (IllegalAccessException | InstantiationException | IncoherentTypeException e) {
                    e.printStackTrace();
                }
            }
            return output;
        }


        @Override
        public Kolumna doMathOperationWithValue(Value val, String operation) throws IncoherentTypeException, DivideByZeroException {
            try {
                return convertToKolumna().doMathOperationWithValue(val, operation);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throw new RuntimeException("blad w doMathOperationWithValue");
        }

        @Override
        public Kolumna doMathOperationWithOtherColumn(Kolumna kol, String operation) throws DifferentSizeOfColumnsExcepiton, IncoherentTypeException {
            try {
                return convertToKolumna().doMathOperationWithOtherColumn(kol, operation);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throw new RuntimeException("blad w doMathOperationWithOtherColumn");
        }

        //        @Override
//        public Kolumna add(Value val) throws IncoherentTypeException {
//            try {
//                return convertToKolumna().add(val);
//            } catch (SQLException e) {
//                e.printStackTrace();
//                throw new RuntimeException("blad w add");
//            }
//        }

        @Override
        public void dodaj(Value element) throws IncoherentTypeException {
            try {
                stmt = connection.createStatement();
                String value=element.toString();
                int added;
                added = stmt.executeUpdate(String.format("INSERT into %s(%s) VAlUES(%s)",tableName,getNazwa(),value));
                ilosc_wierszy+=added;

            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("jakis blad w dodajElement"); //todo: zrobic to lepiej
            }
        }

        @Override
        public Value zwrocObiekt(int index) {
            connect();
            try {
                stmt = connection.createStatement();

                rs = stmt.executeQuery(String.format("SELECT %s FROM %s", colNames[index], tableName));
                Value output = colTypes[index].newInstance().create(rs.getString(index));
                return output;
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            return null; //todo: zrobic to lepiej
        }

        @Override
        public int size() {
            connect();
            try {
                stmt = connection.createStatement();

                rs = stmt.executeQuery(String.format("SELECT count(%s) FROM %s", colNames[index], tableName));
                rs.next();
                return rs.getInt(1);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return -1; //todo:zrobic lepiej
        }


        @Override
        public String toString() {
            StringBuilder bufor = new StringBuilder();

            bufor.append(colNames[index]).append(":").append(colTypes[index].getSimpleName());
            bufor.append("\n");

            connect();
            try {
                stmt = connection.createStatement();
                rs = stmt.executeQuery(String.format("SELECT %s FROM %s", colNames[index], tableName));
                while (rs.next()) {
                    bufor.append(rs.getString(colNames[index]));
                    bufor.append("\n");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return bufor.toString();

        }

    }


    @Override
    public GroupBy groupBy(String...colname) throws ZeroLengthException, NoSenseInGroupingByAllColumnsException {
        return new GrupatorDF(colname);
    }


    class GrupatorDF implements GroupBy {
        String namesInGroupBy;
        String[] otherNames;
        String[] otherNumeric;

        Map <Integer,DataFrame> groupedDF;
        DataFrame values;


        GrupatorDF(String... colnames) {
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < colnames.length; i++) {
                if (!(i == colnames.length-1)) {
                    s.append(colnames[i]).append(",");
                } else {
                    s.append(colnames[i]);
                }
            }
            namesInGroupBy = s.toString();

            ArrayList<String> ls = new ArrayList<>(Arrays.asList(zwroc_nazwy()));
            ls.removeAll(Arrays.asList(colnames));
            otherNames=ls.toArray(new String[0]);

            Class<? extends Value> []types = zwroc_typy();

            for (int i = 0; i < types.length; i++) {
                if(!NumericValue.class.isAssignableFrom(get(colNames[i]).getType())){
                    ls.remove(colNames[i]);
                }
            }
            otherNumeric=ls.toArray(new String[0]);
        }

        @Override
        public DataFrame max() throws ZeroLengthException {
            return applyFunction("max",otherNames);
        }


        @Override
        public DataFrame min() throws ZeroLengthException {
            return applyFunction("min",otherNames);
        }

        @Override
        public DataFrame sum() throws ZeroLengthException {
            return applyFunction("sum",otherNumeric);
        }

        @Override
        public DataFrame var() throws ZeroLengthException {
            return applyFunction("variance",otherNumeric);
        }

        @Override
        public DataFrame mean() throws ZeroLengthException {
            return applyFunction("avg",otherNumeric);
        }

        @Override
        public DataFrame std() throws ZeroLengthException {
            return applyFunction("std",otherNumeric);
        }
        public DataFrame applyFunction(String function,String  [] OtherNames) throws ZeroLengthException {
            connect();
            StringBuilder s=new StringBuilder();
            try {
                stmt = connection.createStatement();

                for (int i = 0; i < OtherNames.length; i++) {

                    if (!(i == OtherNames.length-1)) {
                        s.append(function).append("(").append(OtherNames[i]).append(") as ").append(OtherNames[i]).append(", ");
                    } else {
                        s.append(function).append("(").append(OtherNames[i]).append(") as ").append(OtherNames[i]);
                    }
                }
                String colNamesInMax = s.toString();
                String sql = (String.format("SELECT %s, %s FROM %s group by %s order by %s",namesInGroupBy,colNamesInMax,tableName,namesInGroupBy,namesInGroupBy));
                System.out.println(sql);
                rs = stmt.executeQuery(sql);
                return DFfromSelect(rs);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new ZeroLengthException("Nie udalo się cos w f.agregujacej");
            }
        }

        @Override
        public DataFrame apply(Applyable funkcja) throws IncoherentTypeException, ZeroLengthException {
            if(groupedDF ==null){
                makeGroups();
            }

            DataFrame output=null;
            for (int i = 0; i < values.size(); i++) {

                DataFrame group= funkcja.apply(groupedDF.get(i));
                if(output==null){
                    output = getOutputDataFrame(group);
                }


                Value[]keysVal=values.zwrocWiersz(i);
                Value[]rowValues=new Value[group.iloscKolumn()+values.iloscKolumn()];
                System.arraycopy(keysVal,0,rowValues,0,keysVal.length);

                for (int j = 0; j < group.size(); j++) {
                    Value[] groupValues=group.zwrocWiersz(j);
                    System.arraycopy(groupValues,0,rowValues,keysVal.length,groupValues.length);
                }

                try {
                    output.dodajElement(rowValues);
                } catch (DifferentAmountOfColumns differentAmountOfColumns) {
                    differentAmountOfColumns.printStackTrace();
                }
            }


            return output;
        }

        public DataFrame getOutputDataFrame(DataFrame group) {
            DataFrame output;//typy
            Class<? extends Value>[] dfTypes = group.zwroc_typy();
            Class<? extends Value>[] keyTypes= values.zwroc_typy();

            Class<? extends Value>[] fullTypes = new Class[keyTypes.length+dfTypes.length];
            System.arraycopy(keyTypes,0,fullTypes,0,keyTypes.length);
            System.arraycopy(dfTypes,0,fullTypes,keyTypes.length,dfTypes.length);

            //nazwy
            String[] dfNames = group.zwroc_nazwy();
            String[] keyNames= values.zwroc_nazwy();

            String[] fullNames = new String[keyNames.length+dfNames.length];
            System.arraycopy(keyNames,0,fullNames,0,keyNames.length);
            System.arraycopy(dfNames,0,fullNames,keyNames.length,dfNames.length);

            output=new DataFrame(fullNames,fullTypes);
            return output;
        }

        public void makeGroups() {
            try {
            groupedDF =new HashMap<Integer, DataFrame>();

            String [] group_col_id =namesInGroupBy.split(",");
            connect();

                stmt = connection.createStatement();
                rs = stmt.executeQuery(String.format("SELECT distinct %s FROM %s  order by %s", namesInGroupBy, tableName,namesInGroupBy));
                values=DFfromSelect(rs);
                //keys = new ArrayList<String>(values.size());

                for (int i = 0; i < values.size(); i++) {
                    StringBuilder s = new StringBuilder();
                    Value[]row= values.zwrocWiersz(i);
                    for (int j = 0; j < values.iloscKolumn(); j++) {
                        s.append(group_col_id[j]).append("=").append("'").append(row[j].toString()).append("'");

                        if(j<values.iloscKolumn()-1){
                            s.append(" and ");
                        }
                    }
                    String key = s.toString();
                    //keys.add(i,key);

                    String othernames=String.join(",",otherNames);


                    rs=stmt.executeQuery(String.format("select %s from %s where %s",othernames,tableName,key));
                    DataFrame group=DFfromSelect(rs);
                    groupedDF.put(i,group);
                }
            } catch (SQLException e) {
                groupedDF =null;
                e.printStackTrace();
            }
        }
    }



    //----------------------------------------------------//

    public static DataFrame DFfromSelect(ResultSet select) throws SQLException {
        ResultSetMetaData mt = select.getMetaData();
        int colCount = mt.getColumnCount();
        String[] names = new String[colCount];
        Class<? extends Value>[] types = new Class[colCount];

        for (int i = 0; i < colCount; i++) {
            names[i] = mt.getColumnName(i + 1);
            types[i] = lab0.dataframe.values.TypeEnum.Type.getValueType(mt.getColumnType(i + 1));
        }

        DataFrame output = new DataFrame(names, types);
        Value[] row = new Value[colCount];

        while (select.next()) {
            for (int i = 0; i < row.length; i++) {
                try {
                    row[i] = types[i].newInstance().create(select.getString(names[i]));
                } catch (IllegalAccessException | InstantiationException e) {
                    e.printStackTrace();
                }
            }
            try {
                output.dodajElement(row);
            } catch (IncoherentTypeException | DifferentAmountOfColumns differentAmountOfColumns) {
                differentAmountOfColumns.printStackTrace();
            }
        }

        return output;
    }


    public DataFrame fromDataFrameDFtoDataFrame() throws SQLException {
        Statement stmt = this.connection.createStatement();
        rs = stmt.executeQuery(String.format("SELECT * FROM %s", tableName));
        return DFfromSelect(rs);
    }

}
