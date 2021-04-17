# Airline Reservation System

There are three main actors (user roles). Passenger, agent and admin.
• Passengers can:
o Viewlistofairports
o Viewlistofairlinesflyingoutofanairport(searchbyairportthreelettercode) o Viewlistofflightsbetweenadepartureanddestinationforadate
o Viewlistofownreservations
o Viewdetailsofareservation(flights,departuretimes,etc.)
o Makeareservation(note:inputwillbealistofflights)
o Cancelareservation
o Confirmandpurchaseareservation.Thiswillresultinmultipletickets(oneforeach
flight in the reservation)
• Agents (this role is optional and extra credit):
o Viewlistofairports
o Viewlistofairlinesflyingoutofanairport(searchbyairportthreelettercode)
o Viewlistofflightsbetweenadepartureanddestinationforadate
o Viewlistofpassengersandreservationsmadeforthem“bythisagent”
o Viewdetailsofareservation(flights,departuretimes,etc.)–onlyifdonebythisagent o Makeareservation(note:payloadwillbealistofflights)
o Cancelareservation
o Confirmandpurchaseareservation.Thiswillresultinmultipletickets(oneforeach
flight in the reservation) • Admins
o CanperformCRUDoperationsonallresources
