-module(exchange).
-export([start/0,mainProcess/0,printList/1,forkChilds/1,goToListeningMode/0]).
-import(calling,[childProcess/2]).

start()->
	mainProcess().

mainProcess()->
	{ok,CallingList} = file:consult("calls.txt"),
	io: fwrite("~n **Calls to be made** ~n"),
	printList(CallingList),
	register(masterProcess, self()),
	forkChilds(CallingList),
	goToListeningMode().

printList([])->ok;
printList([Head|Tail])->
	io: fwrite("~w: ~w~n",[element(1,Head),element(2,Head)]),
	printList(Tail).

forkChilds([])->ok;
forkChilds([Head|Tail])->
	register(element(1,Head),spawn(calling,childProcess,[element(1,Head),element(2,Head)])),
	forkChilds(Tail).

goToListeningMode()->
	receive{To,From,Message,Timestamp}->
		io: fwrite("~n~w received ~w message from ~w [~w]",[To,Message,From,Timestamp]),
		goToListeningMode()
	after 1500->
		io:fwrite("~n~nMaster has received no replies for 1.5 seconds, ending...~n")
	end.

