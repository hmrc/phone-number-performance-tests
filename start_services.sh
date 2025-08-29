#!/usr/bin/env bash

sm2 --start PHONE_NUMBER_ALL --appendArgs '{
   "PHONE_NUMBER_VERIFICATION": [
       "-Dmicroservice.services.access-control.allow-list.0=pni-performance-tests",
       "-Dplay.http.router=testOnlyDoNotUseInAppConf.Routes"
   ]
}'