#!/usr/bin/python

import csv
from dbfpy import dbf
import os
import sys

filename = sys.argv[1]
print "Converting %s to csv" % filename
csv_fn = filename[:-4]+ ".csv"
with open(csv_fn,'wb') as csvfile:
    in_db = dbf.Dbf(filename)
    out_csv = csv.writer(csvfile)
    names = []
    for field in in_db.header.fields:
        names.append(field.name)
    out_csv.writerow(names)
    for rec in in_db:
        row = []
        for f in rec.fieldData:
            if f.__class__.__name__ == "str":
                row.append(f.decode('cp866').encode('UTF-8'))
            else:
                row.append(f)
        out_csv.writerow(row)

    in_db.close()
    print "Done..."
