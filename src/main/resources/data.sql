INSERT INTO `user` (id, username, password, role)
              VALUES (1,'miroslav','$2y$12$NH2KM2BJaBl.ik90Z1YqAOjoPgSd0ns/bF.7WedMxZ54OhWQNNnh6','KORISNIK');
INSERT INTO `user` (id, username, password, role)
              VALUES (2,'tamara','$2y$12$DRhCpltZygkA7EZ2WeWIbewWBjLE0KYiUO.tHDUaJNMpsHxXEw9Ky','KORISNIK');
INSERT INTO `user` (id, username, password, role)
              VALUES (3,'petar','$2y$12$i6/mU4w0HhG8RQRXHjNCa.tG2OwGSVXb0GYUnf8MZUdeadE4voHbC','KORISNIK');

              
INSERT INTO delivery (id, jmbg, licna, ime) VALUES (1, '0125', '0125', 'Pera Peric');
INSERT INTO delivery (id, jmbg, licna, ime) VALUES (2, '0256', '0625', 'Jova Peric');
INSERT INTO delivery (id, jmbg, licna, ime) VALUES (3, '9635', '7896', 'Zoka Peric');

INSERT INTO narudzbina (id, number, datum, adress, price, delivery_id) 
	VALUES (1, 555, '2020-06-21', 'Kotorska', 400.0, 1);
INSERT INTO narudzbina (id, number, datum, adress, price, delivery_id) 
	VALUES (2, 789, '2020-07-01', 'Karadjordjeva', 450.0, 1);
INSERT INTO narudzbina (id, number, datum, adress, price, delivery_id) 
	VALUES (3, 589, '2021-01-01', 'Karadjordjeva', 457.0, 2);
INSERT INTO narudzbina (id, number, datum, adress, price, delivery_id) 
	VALUES (4, 889, '2021-12-17', 'Ulica4', 1256.0, 3);
INSERT INTO narudzbina (id, number, datum, adress, price, delivery_id) 
	VALUES (5, 359, '2021-12-17', 'Ulica4', 965.0, 2);
	
INSERT INTO bill (id, number, datum, price, order_id) VALUES (1, 555, '2020-06-21', 400.0, 1);
INSERT INTO bill (id, number, datum, price, order_id) VALUES (2, 789, '2020-07-01', 450.0, 2);
INSERT INTO bill (id, number, datum, price, order_id) VALUES (3, 589, '2021-01-01', 457.0, 3);
INSERT INTO bill (id, number, datum, price, order_id) VALUES (4, 889, '2021-12-17', 1256.0, 4);