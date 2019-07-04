-module(calling).
-export([childProcess/2,messageSend/2,goToRecievingMode/1]).

childProcess(Sender,Reciever)->
	messageSend(Reciever,Sender),
	goToRecievingMode(Sender).

messageSend([],Sender)->ok;
messageSend([Head|Tail],Sender)->
	timer:sleep(rand:uniform(100)),
	Head! {Head,Sender,intro,element(3,erlang:now())},
	messageSend(Tail,Sender).

goToRecievingMode(ThreadName)->
	receive
		{To,From,intro,Timestamp}->
			masterProcess! {To,From,intro,Timestamp},
			timer:sleep(rand:uniform(100)),
			From! {From,To,reply,Timestamp},
			goToRecievingMode(ThreadName);
		{To,From,reply,Timestamp}->
			masterProcess! {To,From,reply,Timestamp},
			goToRecievingMode(ThreadName)
	after 1000 ->
		io:fwrite("~n~nProcess ~w has received no calls for 1 second, ending...",[ThreadName])
	end.
		