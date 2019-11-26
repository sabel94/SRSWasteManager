# Calculate averages for waste amount over users from database.json
import json
from dataGeneratingScript import YEARS

f = open("database.json", "r")
obj = json.load(f)

number_of_users = len(obj.items())
sums = {
    str(year): [0]*12 for year in YEARS
}

for userId, userData in obj.items():
    wasteStats = userData["wasteStats"]
    for year, yearData in wasteStats.items():
        for month, monthData in enumerate(yearData):
            sums[year][month] += monthData["householdWaste"] + monthData["plasticPackaging"] + monthData["newspapers"]

averages = {year: [sums[year][month] / number_of_users for month in range(len(sums[year]))] for year in YEARS}

outfile = open("averages.json", "w")
outfile.write(json.dumps(averages, indent=4))
