SELECT DISTINCT a.id AS id , a.code AS code , v_p.id AS patientId , v_p.birthTime AS birthTime , a.effectiveTime_start AS effectiveTime 
			FROM 
			act a 
			INNER JOIN participation p ON (a.id = p.actId)
			INNER JOIN v_person v_p ON (p.entityId = v_p.id) 
			
			WHERE ((a.code IN (isAnySubclassOf(30630007))))
			;