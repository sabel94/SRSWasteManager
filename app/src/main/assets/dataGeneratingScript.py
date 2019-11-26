import json
import numpy as np

NO_OF_USERS = 100 # Number of users to create
YEARS = ["2019"]
DAYS_IN_MONTH = {0: 31, 1: 28, 2: 31, 3: 30, 4: 31, 5: 30, 6: 31, 7: 31, 8: 30, 9: 31, 10: 30, 11: 31}
STD_DEVS = {"householdWaste": 1, "plasticPackaging": 0.1, "newspapers": 0.15}
PRICES = {"wastePerKilo": 1}

FIRST_NAMES = ["Karl-Einar", "Rickard", "Johan", "Jesper", "Axel", "Oscar", "Jon", "Mattias", "Jonas", "Erik", "Leo", "Staffan", "Annelie", "Sara", "Ida", "Emma", "Karl", "Gustav", "Albin", "Isak", "Adam", "Robin"]
LAST_NAMES = ["Renström", "Bard", "Åkesson", "Hamilton", "Andersson", "Eriksson", "Broström", "Sabel", "Hagström", "Karlsson", "Niskanen", "Kokkonen", "Nordlund", "Marklund", "Johansson", "Kingstedt", "Studsare", "Sandkvist"]

MEANS =  \
{
	0:
		{"householdWaste": 16.5, "plasticPackaging": 1.6, "newspapers": 3.5},
	1:
		{"householdWaste": 13, "plasticPackaging": 2, "newspapers": 3},
	2:
		{"householdWaste": 17.5, "plasticPackaging": 2, "newspapers": 0.5},
	3:
		{"householdWaste": 19.5, "plasticPackaging": 0, "newspapers": 0},
	4:
		{"householdWaste": 19.5, "plasticPackaging": 2.5, "newspapers": 5},
	5:
		{"householdWaste": 14.6, "plasticPackaging": 0.5, "newspapers": 2.5},
	6:
		{"householdWaste": 13.5, "plasticPackaging": 0.7, "newspapers": 1.7},
	7:
		{"householdWaste": 18.5, "plasticPackaging": 1.7, "newspapers": 2.5},
	8:
		{"householdWaste": 15, "plasticPackaging": 1, "newspapers": 2.2},
	9:
		{"householdWaste": 15.8, "plasticPackaging": 2, "newspapers": 2.5},
	10:
		{"householdWaste": 15.5, "plasticPackaging": 2.4, "newspapers": 3},
	11:
		{"householdWaste": 17, "plasticPackaging": 1.4, "newspapers": 2.5}
}

def get_random_stats_for_one_month(month):
	household_sample = max(0, np.random.normal(MEANS[month]["householdWaste"], STD_DEVS["householdWaste"]))
	plastic_sample = max(0, np.random.normal(MEANS[month]["plasticPackaging"], STD_DEVS["plasticPackaging"]))
	newspapers_sample = max(0, np.random.normal(MEANS[month]["newspapers"], STD_DEVS["newspapers"]))

	return {"householdWaste": household_sample,
			"plasticPackaging": plastic_sample,
			"newspapers": newspapers_sample}



def generate_user_waste_stats(id):
	first_name_idx = np.random.randint(0, len(FIRST_NAMES))
	last_name_idx = np.random.randint(0, len(LAST_NAMES))
	first_name = FIRST_NAMES[first_name_idx]
	last_name = LAST_NAMES[last_name_idx]
	return {
		"name": first_name + " " + last_name,
		"id": id,
		"wasteStats": {
			year: [
				get_random_stats_for_one_month(month) for month in DAYS_IN_MONTH.keys()
			] for year in YEARS
		}
	}


def generate_user_payments(user_waste_stats):
	payments = {}
	for year, year_stats in user_waste_stats["wasteStats"].items():
		payments[year] = []
		for month, month_stats in enumerate(year_stats):
			if month > 10:  # if greater than November
				continue
			waste_amount_month = 0
			for _, waste_kgs in month_stats.items():
				waste_amount_month += waste_kgs
			price_month = waste_amount_month * PRICES["wastePerKilo"]
			payment_month = month + 1
			payment_month_formatted = str(payment_month) if payment_month > 9 else "0" + str(payment_month)
			payments[year].append({"month": month, "total_amount": price_month, "payment_date": "{}-{}-{}".format(year if month < 11 else str(int(year) + 1), payment_month_formatted if month < 11 else "01", DAYS_IN_MONTH[month + 1] if month < 11 else DAYS_IN_MONTH[0])})
	return payments

json_object = {}

for id in range(NO_OF_USERS):
	user_waste_stats = generate_user_waste_stats(id)
	json_object[id] = user_waste_stats
	json_object[id]["payments"] = generate_user_payments(user_waste_stats)

for key, user_object in json_object.items():
	for key, value in user_object.items():
		if key == "wasteStats":
			for year, year_list in value.items():
				if year == "2019":
					for month, month_object in enumerate(year_list):
						if month == "december":
							for wasteType, amount in month_object.items():
								month_object[wasteType] = amount / 3

f = open("database.json", "w")
f.write(json.dumps(json_object, indent=4))

