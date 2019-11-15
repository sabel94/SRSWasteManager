import json
import numpy as np

NO_OF_USERS = 100 # Number of users to create
YEARS = ["2019"]
DAYS_IN_MONTH = {"january": 31, "february": 28, "march": 31, "april": 30, "may": 31, "june": 30, "july": 31, "august": 31, "september": 30, "october": 31, "november": 30, "december": 31}
STD_DEVS = {"householdWaste": 1, "plasticPackaging": 0.1, "newspapers": 0.15}
MONTH_NUMBERS = {"january": 1, "february": 2, "march": 3, "april": 4, "may": 5, "june": 6, "july": 7, "august": 8, "september": 9, "october": 10, "november": 11, "december": 12}
PRICES = {"householdWastePerKilo": 1}

FIRST_NAMES = ["Karl-Einar", "Rickard", "Johan", "Jesper", "Axel", "Oscar", "Jon", "Mattias", "Jonas", "Erik", "Leo", "Staffan", "Annelie", "Sara", "Ida", "Emma", "Karl", "Gustav", "Albin", "Isak", "Adam", "Robin"]
LAST_NAMES = ["Renström", "Bard", "Åkesson", "Hamilton", "Andersson", "Eriksson", "Broström", "Sabel", "Hagström", "Karlsson", "Niskanen", "Kokkonen", "Nordlund", "Marklund", "Johansson", "Kingstedt", "Studsare", "Sandkvist"]

MEANS =  \
{
	"january":
		{"householdWaste": 16.5, "plasticPackaging": 1.6, "newspapers": 3.5},
	"february":
		{"householdWaste": 13, "plasticPackaging": 2, "newspapers": 3},
	"march":
		{"householdWaste": 17.5, "plasticPackaging": 2, "newspapers": 0.5},
	"april":
		{"householdWaste": 19.5, "plasticPackaging": 0, "newspapers": 0},
	"may":
		{"householdWaste": 19.5, "plasticPackaging": 2.5, "newspapers": 5},
	"june":
		{"householdWaste": 14.6, "plasticPackaging": 0.5, "newspapers": 2.5},
	"july":
		{"householdWaste": 13.5, "plasticPackaging": 0.7, "newspapers": 1.7},
	"august":
		{"householdWaste": 18.5, "plasticPackaging": 1.7, "newspapers": 2.5},
	"september":
		{"householdWaste": 15, "plasticPackaging": 1, "newspapers": 2.2},
	"october":
		{"householdWaste": 15.8, "plasticPackaging": 2, "newspapers": 2.5},
	"november":
		{"householdWaste": 15.5, "plasticPackaging": 2.4, "newspapers": 3},
	"december":
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
			year: {
				month: get_random_stats_for_one_month(month) for month in DAYS_IN_MONTH.keys()
			} for year in YEARS
		}
	}


def generate_user_payments(user_waste_stats):
	payments = {}
	for year, year_stats in user_waste_stats["wasteStats"].items():
		payments[year] = {}
		for month, month_stats in year_stats.items():
			if MONTH_NUMBERS[month] > 10:
				continue
			waste_amount_month = 0
			for _, waste_kgs in month_stats.items():
				waste_amount_month += waste_kgs
			price_month = waste_amount_month * PRICES["householdWastePerKilo"]
			# TODO: Implement with date instead
			payment_month = MONTH_NUMBERS[month] + 1
			payment_month_formatted = str(payment_month) if payment_month > 9 else "0" + str(payment_month)
			payments[year][month] = {"total_amount": price_month, "payment_date": "{}-{}-{}".format(year, payment_month_formatted, DAYS_IN_MONTH[month])}
	return payments

json_object = {}

for id in range(NO_OF_USERS):
	user_waste_stats = generate_user_waste_stats(id)
	json_object[id] = user_waste_stats
	json_object[id]["payments"] = generate_user_payments(user_waste_stats)

for key, user_object in json_object.items():
	for key, value in user_object.items():
		if key == "wasteStats":
			for year, year_object in value.items():
				if year == "2019":
					for month, month_object in year_object.items():
						if month == "december":
							for wasteType, amount in month_object.items():
								month_object[wasteType] = amount / 3

f = open("database.json", "w")
f.write(json.dumps(json_object, indent=4))

