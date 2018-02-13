package com.han;

import com.han.bean.HFilter;
import com.han.bean.HScan;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterBase;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HBaseJsonScan {
    private static final Logger logger = LoggerFactory.getLogger(HBaseJsonScan.class);
    public static Scan transfromHScan(HScan hScan){
        Scan scan = new Scan();
        if(hScan.getStartRowKey()!= null && hScan.getEndRowKey() != null){
            scan.setStartRow(Bytes.toBytes(hScan.getStartRowKey()));
            scan.setStopRow(Bytes.toBytes(hScan.getEndRowKey()));
        }
        if(hScan.getFilterListType() == null || hScan.getFilterList() == null){
            return scan;
        }
        List<HFilter> list = hScan.getFilterList();

        List<Filter> filterList = new ArrayList<Filter>();
        hScan.getFilterList().forEach(hFilter -> {
            Filter filter = null;
            ArrayList<byte[]> filterArguments = new ArrayList<byte[]>();
            Arrays.asList(hFilter.getParams()).forEach(c->{
                filterArguments.add(Bytes.toBytes(c));
            });
            try {
                Method method = Class.forName(new StringBuffer("org.apache.hadoop.hbase.filter.").append(hFilter.getFilterName()).toString()).getDeclaredMethod("createFilterFromArguments",ArrayList.class);
                filter = (FilterBase) method.invoke(null,filterArguments);
            } catch (NoSuchMethodException e) {
                logger.error("transform catch a exception {}",e.getClass().getName());
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                logger.error("transform catch a exception {}",e.getClass().getName());
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                logger.error("transform catch a exception {}",e.getClass().getName());
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                logger.error("transform catch a exception {}",e.getClass().getName());
                e.printStackTrace();
            }
            filterList.add(filter);

        });
        Filter filters;
        if(hScan.getFilterListType().trim().equals("or")){
            filters = new FilterList(FilterList.Operator.MUST_PASS_ONE,filterList);
        }else{
            filters = new FilterList(FilterList.Operator.MUST_PASS_ALL,filterList);
        }
        scan.setFilter(filters);
        return scan;
    }
}
