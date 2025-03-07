--  Check if the activation code is expired
CREATE OR REPLACE FUNCTION is_activation_active (activation_id VARCHAR)
    RETURNS BOOLEAN AS $$
DECLARE
    expiration  TIMESTAMP;

BEGIN
    SELECT expired_at INTO expiration
    FROM account_activation WHERE id = activation_id;

    RETURN current_timestamp < expiration;
END;
$$ LANGUAGE plpgsql;

-- Check the remaining tickets of a given ticket type
CREATE OR REPLACE FUNCTION get_event_left_ticket_of_given_ticket_type (event_id VARCHAR, subject_ticket_type ticket_type)
    RETURNS INT8 AS $$
DECLARE
    result              int8;
    ticket_price_id     VARCHAR(41);
    total_ticket	    INT8 := 0;
    sold_ticket 	    INT8 := 0;
BEGIN
    SELECT id INTO ticket_price_id
    FROM ticket_price
    WHERE id_event = event_id
      AND id_ticket_type = (SELECT tkt.id FROM tickets_type tkt WHERE tkt.ticket_type = subject_ticket_type);

    SELECT max_number INTO total_ticket
    FROM ticket_price tp
    WHERE tp.id = ticket_price_id;

    SELECT COUNT(*) INTO sold_ticket
    FROM ticket t
    WHERE id_ticket_price = ticket_price_id;

    result := total_ticket - sold_ticket;

    RETURN result;
END;
$$ LANGUAGE plpgsql;