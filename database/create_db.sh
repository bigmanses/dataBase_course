#!/bin/sh

sudo su -c 'psql -c "CREATE USER stock WITH PASSWORD '"'"'stock'"'"' ;"' - postgres
sudo su -c 'createdb stock -O stock; ' - postgres
psql -U stock -d stock -a -f create_schema.sql
#psql -U stock -d stock -a -f insert_init_data.sql