(defvar A )
(defvar B ) 
(defvar C ) 
(defvar D )

(write-line "Enter A: ") 
(setq A (read)) 
(format t "~v,'~b" 32 A)
(terpri)

(write-line "Enter B: ") 
(setq B (read))
(format t "~v,'~b" 32 B) 
(terpri)(terpri)

(sb-thread:make-thread 
	(lambda () 
		(progn 
			(sleep 1)
			(setq C (+ A B)) (terpri)
			(write-line "Addition Of Two Numbers: ")
			(format t "~v,'~b" 64 C)(terpri)
			(format t "Addition in decimal : ~$" C)(terpri)
			(format t "~A" sb-thread:*current-thread*)			
			(terpri)(terpri)
			
		)
	)
)



(sb-thread:make-thread 
	(lambda () 
		(progn 
			(sleep 2)
					
			(setq c (- A B)) 
			(write-line "substraction Of Two Numbers: ") 
			(format t "~v,'~b" 64 C)(terpri)
			(format t "Substraction in decimal : ~$" C)(terpri)
			(format t "~A" sb-thread:*current-thread*)
			(terpri)(terpri)
			
		)
	)
)

	 
(sb-thread:make-thread 
	(lambda () 
		(progn 
			(sleep 3)
			(setq c (* A B))
			(write-line "Multiplication Of Two Numbers: ") 
			(format t "~v,'~b" 64 C)(terpri)
			(format t "Multiplication in decimal :~$" C)(terpri)
			(format t "~A" sb-thread:*current-thread*)
			(terpri)(terpri)
						
		)
	)
)

(sb-thread:make-thread 
	(lambda () 
		(progn 
			(sleep 4)
			
			(setq c (/ A B)) 
			(write-line "Divison Of Two Numbers: ") 
			(format t "~v,'~b" 64 C)(terpri)
			(format t "Divison in decimal : ~$" C)(terpri)
			(format t "~A" sb-thread:*current-thread*)
			(terpri)(terpri)
			
		)
	)
)
(print(sb-thread:list-all-threads))(terpri)(terpri)
			
