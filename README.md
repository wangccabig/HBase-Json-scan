## Tips about HBase Json Scan
Read data from HBase,there support a scan defined by json to describ filters and rowkey range.
```
{
                "start_rowkey": "000",
                "end_rowkey": "111",
                "filter_list_type": "and",
                "filter_list": [{
                                "filter_class_name": "SingleColumnValueFilter",
                                "params": ["'cf'",
                                "'age'",
                                ">",
                                "'binary:11'"]
                },
                {
                                "filter_class_name": "RowFilter",
                                "params": [">",
                                "'binary:002'"]
                }]
}
``` 
commentate： 

* ** start_rowkey **  start rowkey
* ** end_rowkey ** end rowkey
* ** filter_list_type ** the relation in the filter list below
* ** filter_list ** fliter list
* ** filter_class_name ** fliter class name, SingleColumnValueFilter, RowFilter et.
* ** params ** have to include params which create a filter above,the params like the hbase shell params.