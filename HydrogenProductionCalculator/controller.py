from productionCalculator import productionCalculator as backend

calc = backend()
backend.readFile(calc, "HydrogenProductionCalculator\curve1.txt")

inflectionPoint = calc.inflectionPoint2()
print(calc.voltages[inflectionPoint])