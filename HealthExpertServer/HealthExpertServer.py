from flask import Flask, request, jsonify
from flask_mysqldb import MySQL
from werkzeug.utils import secure_filename
import json, os, csv
from pyfcm import FCMNotification
import hashlib
from firebase import firebase
from os.path import join, dirname, realpath
from datamining.dataset1.association.prediction import predict

push_service = FCMNotification(api_key='AIzaSyAJAUIw8JlY64FYmft7MfZyyZqlqd18NII')

app = Flask(__name__)
app.config['MYSQL_HOST'] = 'localhost'
app.config['MYSQL_USER'] = 'root'
app.config['MYSQL_PASSWORD'] = ''
app.config['MYSQL_DB'] = 'hedb'
dir = dirname(realpath(__file__))
app.config['UPLOAD_FOLDER'] = join(dir, "static\\uploads\\")
app.config['UPLOAD_PATH'] = "static/uploads/"
mysql = MySQL(app)
ALLOWED_EXTENSIONS = {'png', 'jpg', 'jpeg', 'PNG', 'JPG', 'JPEG'}

authentication = firebase.FirebaseAuthentication('9xOiL0GKPgXmUXO85nYFCFg0pj6H0vdzgnVYUuwy', 'archishthakkar@gmail.com')
firebase = firebase.FirebaseApplication('https://newfirebaseapplication-7755a.firebaseio.com/', authentication)


# JSONResponses
# Register Status


@app.route('/')
def hello_world():
    return 'Hello World!'


@app.route('/auth/register/doctor', methods=['POST', 'GET'])
def register_doctor():
    if request.method == 'POST':
        name = request.form['name']
        emailid = request.form['emailid'].encode('utf-8')
        accesstoken = hashlib.sha256(emailid).hexdigest()
        regid = request.form['regid']
        speciality = request.form['speciality']
        city = request.form['city']
        gender = request.form['gender']
        pincode = request.form['pincode']
        experience = request.form['experience']
        phoneno = request.form['phoneno']
        password = hashlib.sha256(request.form['password'].encode('utf-8')).hexdigest()
        fuid = request.form['fuid'].encode('utf-8')
        file = request.files['image']
        photo = app.config['UPLOAD_PATH'] + accesstoken + file.filename
        filename = secure_filename(accesstoken + file.filename)

        file.save(os.path.join(app.config['UPLOAD_FOLDER'], filename))

        status = 0
        r_id = 2
        cur = mysql.connection.cursor()
        cur.execute(
            '''INSERT INTO doctor(d_name,d_phoneno,d_regid,d_gender,d_speciality,d_experience,d_city,d_pincode,d_accesstoken,d_photo,d_fuid) values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)''',
            (name, phoneno, regid, gender, speciality, experience, city, pincode, accesstoken, photo, fuid))
        cur.execute(
            '''INSERT into users(u_accesstoken,u_emailid,u_password,u_status,r_id) values(%s,%s,%s,%s,%s)''',
            (accesstoken, emailid, password, status, r_id))
        mysql.connection.commit()

        return jsonify(status=True,
                       message="Registered Successfully")


@app.route('/auth/register/patient/icon', methods=['POST', 'GET'])
def patient_register_icon():
    if request.method == 'POST':
        name = request.form['name']
        dob = request.form['dob']
        height = request.form['height']
        weight = request.form['weight']
        emailid = request.form['emailid']
        occupation = request.form['occupation']
        symptoms = request.form['symptoms']
        bloodgroup = request.form['bloodgroup']
        history = request.form['history']
        investigations = request.form['investigations']
        mothersname = request.form['mothersname']
        mothersymptom = request.form['mothersymptom']
        fathername = request.form['fathername']
        fathersymptom = request.form['fathersymptom']
        accesstoken = hashlib.sha256(emailid).hexdigest()
        city = request.form['city']
        gender = request.form['gender']
        pincode = request.form['pincode']
        phoneno = request.form['phoneno']
        password = hashlib.sha256(request.form['password']).hexdigest()
        devicetoken = request.form['devicetoken']
        file = request.files['image']
        photo = app.config['UPLOAD_PATH'] + accesstoken + file.filename
        filename = secure_filename(accesstoken + file.filename)

        file.save(os.path.join(app.config['UPLOAD_FOLDER'], filename))

        status = 1
        r_id = 3
        cur = mysql.connection.cursor()
        cur.execute(
            '''INSERT INTO patient(p_name,p_dob,p_gender,p_height,p_weight,p_accesstoken,p_phoneno,p_occupation,p_symptoms,p_bloodgroup,p_history,p_investigations,p_city,p_pincode,p_mothername,p_mothersymptoms,p_fathername,p_fathersymptoms,p_photo,p_devicetoken) 
              values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)''',
            (name, dob, gender, height, weight, accesstoken, phoneno, occupation, symptoms, bloodgroup, history,
             investigations, city, pincode, mothersname, mothersymptom, fathername, fathersymptom, photo, devicetoken))
        cur.execute('''INSERT into users(u_accesstoken,u_emailid,u_password,u_status,r_id) values(%s,%s,%s,%s,%s)''',
                    (accesstoken, emailid, password, status, r_id))
        mysql.connection.commit()
        return jsonify(status=True,
                       message="Registered Successfully")


@app.route('/auth/register/patient/noicon', methods=['POST', 'GET'])
def patient_register_noicon():
    if request.method == 'POST':
        data = request.data
        dataD = json.loads(data)
        name = dataD['name'].encode('utf-8')
        dob = dataD['dob'].encode('utf-8')
        height = dataD['height'].encode('utf-8')
        weight = dataD['weight'].encode('utf-8')
        emailid = dataD['emailid'].encode('utf-8')
        occupation = dataD['occupation'].encode('utf-8')
        symptoms = dataD['symptoms'].encode('utf-8')
        bloodgroup = dataD['bloodgroup'].encode('utf-8')
        history = dataD['history'].encode('utf-8')
        investigations = dataD['investigations'].encode('utf-8')
        mothersname = dataD['mothername'].encode('utf-8')
        mothersymptom = dataD['mothersymptoms'].encode('utf-8')
        fathername = dataD['fathername'].encode('utf-8')
        fathersymptom = dataD['fathersymptoms'].encode('utf-8')
        accesstoken = hashlib.sha256(emailid).hexdigest()
        city = dataD['city'].encode('utf-8')
        gender = dataD['gender'].encode('utf-8')
        pincode = dataD['pincode'].encode('utf-8')
        phoneno = dataD['phoneno'].encode('utf-8')
        password = hashlib.sha256(dataD['password'].encode('utf-8')).hexdigest()
        devicetoken = dataD['devicetoken'].encode('utf-8')
        status = 1
        r_id = 3
        cur = mysql.connection.cursor()
        cur.execute(
            '''INSERT INTO patient(p_name,p_dob,p_gender,p_height,p_weight,p_accesstoken,p_phoneno,p_occupation,p_symptoms,p_bloodgroup,p_history,p_investigations,p_city,p_pincode,p_mothername,p_mothersymptoms,p_fathername,p_fathersymptoms,p_devicetoken) 
              values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)''',
            (name, dob, gender, height, weight, accesstoken, phoneno, occupation, symptoms, bloodgroup, history,
             investigations, city, pincode, mothersname, mothersymptom, fathername, fathersymptom, devicetoken))
        cur.execute('''INSERT into users(u_accesstoken,u_emailid,u_password,u_status,r_id) values(%s,%s,%s,%s,%s)''',
                    (accesstoken, emailid, password, status, r_id))
        mysql.connection.commit()
        return jsonify(status=True,
                       message="Registered Successfully")


@app.route('/auth/login', methods=['GET', 'POST'])
def user_login():
    if request.method == 'POST':
        data = request.data
        dataD = json.loads(data)
        emailid = dataD['emailid'].encode('utf-8')
        password = hashlib.sha256(dataD['password'].encode('utf-8')).hexdigest()
        cur = mysql.connection.cursor()
        cur.execute(
            '''SELECT u_status,u_accesstoken,r_id,u_emailid FROM users where u_emailid = %s AND u_password= %s LIMIT 1''',
            (emailid, password))
        if cur.rowcount == 0:
            return jsonify(status=False,
                           message="Invalid EmailId / Password")
        else:
            for row in cur:
                if row[0] == 0:
                    return jsonify(status=False,
                                   message="Registration hasn't verified!")
                else:
                    return jsonify(status=True,
                                   message="Login Successful",
                                   accessToken=row[1],
                                   role=row[2])


@app.route('/auth/login/fuid', methods=['GET', 'POST'])
def user_update_fuid():
    if request.method == 'POST':
        data = request.data
        dataD = json.loads(data)
        accesstoken = dataD['accesstoken'].encode('utf-8')
        fuid = dataD['fuid'].encode('utf-8')
        cur = mysql.connection.cursor()
        cur.execute('''UPDATE doctor SET d_fuid=%s WHERE d_accesstoken=%s''',
                    (fuid, accesstoken))
        mysql.connection.commit()
        return jsonify(status=True, message="Login Successful")


@app.route('/doctor/patient/symptoms', methods=['GET', 'POST'])
def get_symptoms():
    if request.method == 'POST':
        cur = mysql.connection.cursor()
        cur.execute('SELECT s_id,s_name FROM symptom ORDER BY s_name ASC')
        list_data = []
        for row in cur:
            dataDict = {'id': row[0], 'sname': row[1]}
            list_data.append(dataDict)
        return jsonify(data=list_data)


@app.route('/doctor/speciality', methods=['GET', 'POST'])
def get_speciality():
    if request.method == 'POST':
        cur = mysql.connection.cursor()
        cur.execute('''SELECT * FROM speciality ORDER BY s_id''')
        list_data = []
        for row in cur:
            dataDict = {'s_id': row[0], 's_name': row[1], 's_description': row[2]}
            list_data.append(dataDict)
        return jsonify(data=list_data)


@app.route('/doctor/mypatients', methods=['GET', 'POST'])
def get_mypatients():
    if request.method == 'POST':
        data = request.data
        dataD = json.loads(data)
        accesstoken = dataD['accessToken'].encode('utf-8')
        cur = mysql.connection.cursor()
        cur.execute('''SELECT * FROM patient where d_accesstoken=%s''', (accesstoken,))
        list_data = []
        for row in cur:
            dataDict = {'pid': row[0],
                        'name': row[1],
                        'dob': row[2],
                        'gender': row[3],
                        'height': row[4],
                        'weight': row[5],
                        'emailid': row[6],
                        'phoneno': row[7],
                        'occupation': row[8],
                        'symptoms': row[9],
                        'history': row[10],
                        'investigations': row[11],
                        'city': row[12],
                        'pincode': row[13],
                        'mothername': row[14],
                        'mothersymptoms': row[15],
                        'fathername': row[16],
                        'fathersymptoms': row[17],
                        'photo': row[18],
                        'devicetoken': row[19]
                        }
            list_data.append(dataDict)

        return jsonify(data=list_data)


@app.route('/patient/profile', methods=['GET', 'POST'])
def patient_profile():
    if request.method == 'POST':
        data = request.data
        dataD = json.loads(data)
        accesstoken = dataD['accesstoken'].encode('utf-8')

        cur = mysql.connection.cursor()
        cur.execute(
            '''SELECT p_id,p_name,p_dob,p_gender,p_height,p_weight,p_phoneno,p_occupation,p_symptoms,p_bloodgroup,p_history,p_investigations,p_city,p_pincode,p_mothername,p_mothersymptoms,p_fathername,p_fathersymptoms,p_photo,p_accesstoken,p_devicetoken FROM patient INNER JOIN users WHERE p_accesstoken=%s ORDER BY p_name ''',
            (accesstoken,))
        list_data = []
        for row in cur:
            psymptoms = row[8]
            msymptoms = row[15]
            fsymptoms = row[17]
            patient_symptoms = []
            mother_symptoms = []
            father_symptoms = []
            if psymptoms:
                for x in psymptoms.split(","):
                    cur = mysql.connection.cursor()
                    cur.execute('SELECT s_id,s_name FROM symptom where s_id = %s', (x,))
                    row1 = cur.fetchone()
                    cur.close()
                    patient_symptoms.append(row1[1])

            if msymptoms:
                for x1 in msymptoms.split(","):
                    cur1 = mysql.connection.cursor()
                    cur1.execute('SELECT s_id,s_name FROM symptom where s_id = %s', (x1,))
                    row2 = cur1.fetchone()
                    mother_symptoms.append(row2[1])

            if fsymptoms:
                for x2 in fsymptoms.split(","):
                    cur2 = mysql.connection.cursor()
                    cur2.execute('SELECT s_id,s_name FROM symptom where s_id = %s', (x2,))
                    row3 = cur2.fetchone()
                    father_symptoms.append(row3[1])

            patient_symptoms_string = ','.join(patient_symptoms)
            mother_symptoms_string = ','.join(mother_symptoms)
            father_symptoms_string = ','.join(father_symptoms)
            dataDict = {'pid': row[0],
                        'name': row[1],
                        'dob': row[2],
                        'gender': row[3],
                        'height': row[4],
                        'weight': row[5],
                        'phoneno': row[6],
                        'occupation': row[7],
                        'symptoms': patient_symptoms_string,
                        'bloodgroup': row[9],
                        'history': row[10],
                        'investigations': row[11],
                        'city': row[12],
                        'pincode': row[13],
                        'mothername': row[14],
                        'mothersymptoms': mother_symptoms_string,
                        'fathername': row[16],
                        'fathersymptoms': father_symptoms_string,
                        'photo': row[18],
                        'accesstoken': row[19],
                        'devicetoken': row[20]
                        }
            list_data.append(dataDict)

        return jsonify(data=list_data)


@app.route('/doctor/patients/prediction', methods=['GET', 'POST'])
def patient_prediction():
    global mother_symptoms, father_symptoms
    if request.method == 'POST':
        data = request.data
        dataD = json.loads(data)
        accesstoken = dataD['accessToken'].encode('utf-8')
        symptoms = dataD['symptoms'].encode('utf-8')
        parent_status = dataD['parent_status']
        buckets = []
        buckets.append(symptoms.decode('utf-8').split(","))
        buckets_string = symptoms.decode('utf-8')

        if parent_status == 1:
            cur = mysql.connection.cursor()
            cur.execute(
                '''SELECT p_mothersymptoms,p_fathersymptoms,p_accesstoken FROM patient WHERE p_accesstoken=%s ''',
                (accesstoken,))
            for row in cur:
                msymptoms = row[0]
                fsymptoms = row[1]
                mother_symptoms = []
                father_symptoms = []
                if msymptoms:
                    for x1 in msymptoms.split(","):
                        cur1 = mysql.connection.cursor()
                        cur1.execute('SELECT s_id,s_name FROM symptom where s_id = %s', (x1,))
                        row2 = cur1.fetchone()
                        mother_symptoms.append(row2[1])

                if fsymptoms:
                    for x2 in fsymptoms.split(","):
                        cur2 = mysql.connection.cursor()
                        cur2.execute('SELECT s_id,s_name FROM symptom where s_id = %s', (x2,))
                        row3 = cur2.fetchone()
                        father_symptoms.append(row3[1])

            buckets.append(mother_symptoms)
            buckets.append(father_symptoms)

        with open(os.path.join(__file__, "../datamining/dataset1/association/", "buckets_new.csv"), 'w',
                  encoding='utf-8', newline='') as csvfile:
            writer = csv.writer(csvfile)
            for bucket in buckets:
                writer.writerow(bucket)
        csvfile.close()

        # Prediction of disease based on symptoms
        res = predict()
        #Cleaning -> Association (Apriori) -> Classification(MultinominalNB)->RES
        return jsonify(result=res)


@app.route('/doctor/patients', methods=['GET', 'POST'])
def get_patients():
    if request.method == 'POST':
        data = request.data
        dataD = json.loads(data)
        accesstoken = dataD['accessToken'].encode('utf-8')

        cur = mysql.connection.cursor()
        cur.execute(
            '''SELECT p_id,p_name,p_dob,p_gender,p_height,p_weight,p_phoneno,p_occupation,p_symptoms,p_bloodgroup,p_history,p_investigations,p_city,p_pincode,p_mothername,p_mothersymptoms,p_fathername,p_fathersymptoms,p_photo,p_accesstoken,p_devicetoken FROM patient INNER JOIN users WHERE p_accesstoken=users.u_accesstoken ORDER BY p_name ''')
        list_data = []
        for row in cur:
            psymptoms = row[8]
            msymptoms = row[15]
            fsymptoms = row[17]
            patient_symptoms = []
            mother_symptoms = []
            father_symptoms = []
            if psymptoms:
                for x in psymptoms.split(","):
                    cur = mysql.connection.cursor()
                    cur.execute('SELECT s_id,s_name FROM symptom where s_id = %s', (x,))
                    row1 = cur.fetchone()
                    cur.close()
                    patient_symptoms.append(row1[1])

            if msymptoms:
                for x1 in msymptoms.split(","):
                    cur1 = mysql.connection.cursor()
                    cur1.execute('SELECT s_id,s_name FROM symptom where s_id = %s', (x1,))
                    row2 = cur1.fetchone()
                    mother_symptoms.append(row2[1])

            if fsymptoms:
                for x2 in fsymptoms.split(","):
                    cur2 = mysql.connection.cursor()
                    cur2.execute('SELECT s_id,s_name FROM symptom where s_id = %s', (x2,))
                    row3 = cur2.fetchone()
                    father_symptoms.append(row3[1])

            patient_symptoms_string = ','.join(patient_symptoms)
            mother_symptoms_string = ','.join(mother_symptoms)
            father_symptoms_string = ','.join(father_symptoms)
            dataDict = {'pid': row[0],
                        'name': row[1],
                        'dob': row[2],
                        'gender': row[3],
                        'height': row[4],
                        'weight': row[5],
                        'phoneno': row[6],
                        'occupation': row[7],
                        'symptoms': patient_symptoms_string,
                        'bloodgroup': row[9],
                        'history': row[10],
                        'investigations': row[11],
                        'city': row[12],
                        'pincode': row[13],
                        'mothername': row[14],
                        'mothersymptoms': mother_symptoms_string,
                        'fathername': row[16],
                        'fathersymptoms': father_symptoms_string,
                        'photo': row[18],
                        'accesstoken': row[19],
                        'devicetoken': row[20]
                        }
            list_data.append(dataDict)

        return jsonify(data=list_data)


@app.route('/admin/doctors', methods=['GET', 'POST'])
def get_doctors():
    if request.method == 'POST':

        cur = mysql.connection.cursor()
        cur.execute(
            '''SELECT DISTINCT d_name,u_emailid,d_phoneno,d_pincode,d_city,s_name,d_gender,d_experience,d_regid,d_accesstoken,d_photo,d_fuid FROM doctor,users,speciality where doctor.d_accesstoken = users.u_accesstoken AND doctor.d_speciality=speciality.s_id  AND users.r_id=2 AND users.u_status=0''')
        list_data = []
        for row in cur:
            dataDict = {'name': row[0],
                        'emailid': row[1],
                        'phoneno': row[2],
                        'pincode': row[3],
                        'city': row[4],
                        'speciality': row[5],
                        'gender': row[6],
                        'experience': row[7],
                        'regid': row[8],
                        'accesstoken': row[9],
                        'photo': row[10],
                        'fuid': row[11]}
            list_data.append(dataDict)

        return jsonify(data=list_data)


@app.route('/admin/doctors/status', methods=['GET', 'POST'])
def status_doctor():
    if request.method == 'POST':
        data = request.data
        dataD = json.loads(data)
        accesstoken = dataD['accesstoken'].encode('utf-8')
        fuid = dataD['firebaseid'].encode('utf-8')
        status = dataD['status']

        cur = mysql.connection.cursor()
        cur.execute(
            '''UPDATE users SET u_status = %s where u_accesstoken = %s''',
            (status, accesstoken))
        mysql.connection.commit()
        if status != 99:
            result = firebase.get('/Users/' + fuid.decode('utf-8'), None)
            registration_id = result.get("device_token")
            name = result.get("name")
            push_service.notify_single_device(registration_id=registration_id, message_title="Health Expert"
                                              , message_body=name + " your registration is verified", sound="default")
            return jsonify(status=True,
                           message="Doctor Verified")
        else:
            return jsonify(status=True,
                           message="Doctor Rejected")


@app.route('/patient/doctors', methods=['GET', 'POST'])
def get_doctors_for_patients():
    if request.method == 'POST':
        data = request.data
        dataD = json.loads(data)
        sname = dataD['s_name'].encode('utf-8')

        cur = mysql.connection.cursor()
        cur.execute(
            '''SELECT DISTINCT d_name,u_emailid,d_phoneno,d_pincode,d_city,s_name,d_gender,d_experience,d_regid,d_accesstoken,d_fuid,d_photo FROM doctor,users,speciality,rating where doctor.d_accesstoken = users.u_accesstoken AND doctor.d_speciality=speciality.s_id  AND users.r_id=2 AND users.u_status=1 AND speciality.s_name=%s ORDER BY rating.r_score DESC''',
            (sname,))
        list_data = []
        for row in cur:
            cur_rate = mysql.connection.cursor()
            cur_rate.execute(
                '''SELECT COUNT(r_like) as likes,ROUND(AVG(r_score),1) as ratings FROM rating where r_daccesstoken=%s''',
                (str(row[9]),))
            for rate_row in cur_rate:
                dataDict = {'name': row[0],
                            'emailid': row[1],
                            'phoneno': row[2],
                            'pincode': row[3],
                            'city': row[4],
                            'speciality': row[5],
                            'gender': row[6],
                            'experience': row[7],
                            'regid': row[8],
                            'accesstoken': row[10],
                            'fuid': row[9],
                            'photo': row[11],
                            'likes': rate_row[0],
                            'ratings': str(rate_row[1])}
                list_data.append(dataDict)

        return jsonify(data=list_data)


@app.route('/doctor/alldoctors', methods=['GET', 'POST'])
def get_doctors_all():
    if request.method == 'POST':

        cur = mysql.connection.cursor()
        cur.execute(
            '''SELECT DISTINCT d_name,u_emailid,d_phoneno,d_pincode,d_city,s_name,d_gender,d_experience,d_regid,d_accesstoken,d_photo,d_fuid FROM doctor,users,speciality,rating where doctor.d_accesstoken = users.u_accesstoken AND doctor.d_speciality=speciality.s_id  AND users.r_id=2 AND users.u_status=1 ORDER BY rating.r_score DESC''')
        list_data = []
        for row in cur:
            cur_rate = mysql.connection.cursor()
            cur_rate.execute(
                '''SELECT COUNT(r_like) as likes,ROUND(AVG(r_score),1) FROM rating where r_daccesstoken=%s''',
                (str(row[9]),))
            for rate_row in cur_rate:
                dataDict = {'name': row[0],
                            'emailid': row[1],
                            'phoneno': row[2],
                            'pincode': row[3],
                            'city': row[4],
                            'speciality': row[5],
                            'gender': row[6],
                            'experience': row[7],
                            'regid': row[8],
                            'accesstoken': row[9],
                            'photo': row[10],
                            'fuid': row[11],
                            'likes': rate_row[0],
                            'ratings': str(rate_row[1])}
                list_data.append(dataDict)

        return jsonify(data=list_data)


@app.route('/bookmark/add', methods=['GET', 'POST'])
def bookmark_operation():
    if request.method == 'POST':
        data = request.data
        dataD = json.loads(data)
        source_token = dataD['source_token'].encode('utf-8')
        destination_token = dataD['destination_token'].encode('utf-8')
        status = dataD['status']
        role = dataD['role']
        cur = mysql.connection.cursor()
        if status == 1:
            cur.execute('''INSERT into bookmarks(b_source,b_destination) VALUES (%s,%s)''',
                        (source_token, destination_token))
            mysql.connection.commit()
            if role == 2:
                return jsonify(status=True, message="Added to My Patients")
            else:
                return jsonify(status=True, message="Added to My Doctors")

        else:
            cur.execute('''DELETE FROM bookmarks WHERE b_source=%s AND b_destination=%s''',
                        (source_token, destination_token))
            mysql.connection.commit()
            if role == 2:
                return jsonify(status=False, message="Removed from My Patients")
            else:
                return jsonify(status=False, message="Removed from My Doctors")


# For patients get all my doctors
@app.route('/patient/bookmark/doctors', methods=['GET', 'POST'])
def bookmark_mydoctors():
    if request.method == 'POST':
        data = request.data
        dataD = json.loads(data)
        source_token = dataD['accesstoken'].encode('utf-8')
        cur = mysql.connection.cursor()
        cur.execute(
            '''SELECT d_name,u_emailid,d_phoneno,d_pincode,d_city,s_name,d_gender,d_experience,d_regid,d_accesstoken,d_fuid,d_photo FROM doctor INNER JOIN users ON doctor.d_accesstoken=users.u_accesstoken INNER JOIN speciality ON speciality.s_id=doctor.d_speciality where doctor.d_accesstoken IN (SELECT b_destination from bookmarks WHERE b_source=%s ORDER BY b_id DESC)''',
            (source_token,))

        list_data = []
        for row in cur:
            cur_rate = mysql.connection.cursor()
            cur_rate.execute(
                '''SELECT COUNT(r_like) as likes,ROUND(AVG(r_score),1) as ratings FROM rating where r_daccesstoken=%s''',
                (str(row[9]),))
            for rate_row in cur_rate:
                dataDict = {'name': row[0],
                            'emailid': row[1],
                            'phoneno': row[2],
                            'pincode': row[3],
                            'city': row[4],
                            'speciality': row[5],
                            'gender': row[6],
                            'experience': row[7],
                            'regid': row[8],
                            'accesstoken': row[10],
                            'fuid': row[9],
                            'photo': row[11],
                            'likes': rate_row[0],
                            'ratings': str(rate_row[1])}
                list_data.append(dataDict)

        return jsonify(data=list_data)


# For doctors get all my patients
@app.route('/doctor/bookmark/patients', methods=['GET', 'POST'])
def bookmark_mypatients():
    if request.method == 'POST':
        data = request.data
        dataD = json.loads(data)
        accesstoken = dataD['accesstoken'].encode('utf-8')
        cur = mysql.connection.cursor()
        cur.execute(
            '''SELECT p_id,p_name,p_dob,p_gender,p_height,p_weight,p_phoneno,p_occupation,p_symptoms,p_bloodgroup,p_history,p_investigations,p_city,p_pincode,p_mothername,p_mothersymptoms,p_fathername,p_fathersymptoms,p_photo,p_accesstoken,p_devicetoken FROM patient where p_accesstoken IN (SELECT b_destination from bookmarks WHERE b_source=%s)''',
            (accesstoken,))
        list_data = []
        for row in cur:
            psymptoms = row[8]
            msymptoms = row[15]
            fsymptoms = row[17]
            patient_symptoms = []
            mother_symptoms = []
            father_symptoms = []
            if psymptoms:
                for x in psymptoms.split(","):
                    cur = mysql.connection.cursor()
                    cur.execute('SELECT s_id,s_name FROM symptom where s_id = %s', (x,))
                    row1 = cur.fetchone()
                    cur.close()
                    patient_symptoms.append(row1[1])

            if msymptoms:
                for x1 in msymptoms.split(","):
                    cur1 = mysql.connection.cursor()
                    cur1.execute('SELECT s_id,s_name FROM symptom where s_id = %s', (x1,))
                    row2 = cur1.fetchone()
                    mother_symptoms.append(row2[1])

            if fsymptoms:
                for x2 in fsymptoms.split(","):
                    cur2 = mysql.connection.cursor()
                    cur2.execute('SELECT s_id,s_name FROM symptom where s_id = %s', (x2,))
                    row3 = cur2.fetchone()
                    father_symptoms.append(row3[1])

            patient_symptoms_string = ','.join(patient_symptoms)
            mother_symptoms_string = ','.join(mother_symptoms)
            father_symptoms_string = ','.join(father_symptoms)
            dataDict = {'pid': row[0],
                        'name': row[1],
                        'dob': row[2],
                        'gender': row[3],
                        'height': row[4],
                        'weight': row[5],
                        'phoneno': row[6],
                        'occupation': row[7],
                        'symptoms': patient_symptoms_string,
                        'bloodgroup': row[9],
                        'history': row[10],
                        'investigations': row[11],
                        'city': row[12],
                        'pincode': row[13],
                        'mothername': row[14],
                        'mothersymptoms': mother_symptoms_string,
                        'fathername': row[16],
                        'fathersymptoms': father_symptoms_string,
                        'photo': row[18],
                        'accesstoken': row[19],
                        'devicetoken': row[20]
                        }
            list_data.append(dataDict)

        return jsonify(data=list_data)


@app.route('/messaging/notify', methods=['GET', 'POST'])
def messaging_notify():
    if request.method == 'POST':
        data = request.data
        dataD = json.loads(data)
        source_fuid = dataD['source_fuid'].encode('utf-8')
        destination_fuid = dataD['destination_fuid'].encode('utf-8')
        message = dataD['message'].encode('utf-8')
        source_fuid = source_fuid.decode('utf-8')
        destination_fuid = destination_fuid.decode('utf-8')
        message = message.decode('utf-8')
        source_result = firebase.get('/Users/' + source_fuid, None)
        result = firebase.get('/Users/' + destination_fuid, None)
        registration_id = result.get("device_token")
        message_title = source_result.get("name")
        message_body = message
        action = dataD['target'].encode('utf-8')
        action = action.decode('utf-8')
        data_message = {'from_did': source_fuid}
        result_push = push_service.notify_single_device(registration_id=registration_id, message_title=message_title,
                                                        message_body=message_body, click_action=action,
                                                        data_message=data_message)
        return jsonify(status=True, message="Success")


@app.route('/notify', methods=['GET', 'POST'])
def notify():
    if request.method == 'POST':
        data = request.data
        dataD = json.loads(data)
        source_fuid = dataD['source_devicetoken'].encode('utf-8')
        destination_fuid = dataD['destination_devicetoken'].encode('utf-8')
        message = dataD['message'].encode('utf-8')
        source_fuid = source_fuid.decode('utf-8')
        destination_fuid = destination_fuid.decode('utf-8')
        message = message.decode('utf-8')
        message_title = "Health Expert"
        message_body = message
        action = dataD['target'].encode('utf-8')
        action = action.decode('utf-8')
        data_message = {'from_did': source_fuid}
        result_push = push_service.notify_single_device(registration_id=destination_fuid, message_title=message_title,
                                                        message_body=message_body, click_action=action,
                                                        data_message=data_message)
        return jsonify(status=True, message="Success")


@app.route('/patient/doctor/feedback', methods=['GET', 'POST'])
def doctor_feedback():
    if request.method == 'POST':
        data = request.data
        dataD = json.loads(data)
        d_accesstoken = dataD['d_accesstoken'].encode('utf-8')
        p_accesstoken = dataD['p_accesstoken'].encode('utf-8')
        rating = dataD['rating']
        vote = dataD['vote']
        cur = mysql.connection.cursor()
        cur.execute('''INSERT INTO rating(r_daccesstoken,r_paccesstoken,r_score,r_like) VALUES (%s,%s,%s,%s) ''',
                    (d_accesstoken, p_accesstoken, rating, vote))

        mysql.connection.commit()

        return jsonify(status=True,
                       message="Thank you for your feedback has been submitted.")


@app.route('/patient/doctor/feedback/check', methods=['GET', 'POST'])
def doctor_feedback_check():
    if request.method == 'POST':
        data = request.data
        dataD = json.loads(data)
        d_accesstoken = dataD['d_accesstoken'].encode('utf-8')
        p_accesstoken = dataD['p_accesstoken'].encode('utf-8')
        cur = mysql.connection.cursor()
        cur.execute('''SELECT * FROM rating WHERE r_daccesstoken=%s AND r_paccesstoken=%s''',
                    (d_accesstoken, p_accesstoken))

        if cur.rowcount > 0:
            return jsonify(status=False,
                           message="You have already given feedback for this doctor.")
        else:
            return jsonify(status=True,
                           message="Grant Feedback.")


@app.route('/doctor/summary', methods=['GET', 'POST'])
def doctor_summary():
    if request.method == 'POST':
        data = request.data
        dataD = json.loads(data)
        accesstoken = dataD['accesstoken'].encode('utf-8')
        cur = mysql.connection.cursor()
        cur.execute('''SELECT ROUND(AVG(r_score),1) as r_score FROM rating WHERE r_daccesstoken = %s''', (accesstoken,))
        row = cur.fetchone()
        score = row[0]
        cur1 = mysql.connection.cursor()
        cur1.execute('''SELECT COUNT(r_like) as r_like FROM rating WHERE r_daccesstoken = %s AND r_like = 1''',
                     (accesstoken,))
        row = cur1.fetchone()
        votes = row[0]
        cur2 = mysql.connection.cursor()
        cur2.execute('''SELECT COUNT(b_source) as r_bookmark FROM bookmarks WHERE b_source = %s''', (accesstoken,))
        row = cur2.fetchone()
        bookmarks = row[0]

        return jsonify(score=str(score), votes=str(votes), bookmarks=str(bookmarks))


@app.route('/patient/summary', methods=['GET', 'POST'])
def patient_summary():
    if request.method == 'POST':
        data = request.data
        dataD = json.loads(data)
        accesstoken = dataD['accesstoken'].encode('utf-8')
        cur = mysql.connection.cursor()
        cur.execute(
            '''SELECT p_id,d_name,s_name,d_photo,p_title,p_prescription,p_timestamp,p_paccesstoken,p_daccesstoken from prescription INNER JOIN doctor ON prescription.p_daccesstoken = doctor.d_accesstoken INNER JOIN speciality ON doctor.d_speciality=speciality.s_id WHERE d_accesstoken IN (SELECT p_daccesstoken FROM prescription WHERE p_paccesstoken = %s)''',
            (accesstoken,))
        list_data = []
        for row in cur:
            dataDict = {'pid': row[0],
                        'dname': row[1],
                        'dspeciality': row[2],
                        'dphoto': row[3],
                        'ptitle': row[4],
                        'pprescription': row[5],
                        'ptimestamp': row[6],
                        'paccesstoken': row[7],
                        'daccesstoken': row[8],
                        }
            list_data.append(dataDict)

        return jsonify(data=list_data)


@app.route('/bookmark/check', methods=['GET', 'POST'])
def bookmark_check():
    if request.method == 'POST':
        data = request.data
        dataD = json.loads(data)
        source_token = dataD['source_token'].encode('utf-8')
        destination_token = dataD['destination_token'].encode('utf-8')
        cur = mysql.connection.cursor()
        cur.execute('''SELECT * FROM bookmarks WHERE b_source=%s AND b_destination=%s LIMIT 1''',
                    (source_token, destination_token))
        if cur.rowcount > 0:
            return jsonify(status=True, message="Present")
        else:
            return jsonify(status=False, message="Absent")


@app.route('/doctor/patient/prescription', methods=['GET', 'POST'])
def doctor_patient_prescription():
    if request.method == 'POST':
        data = request.data
        dataD = json.loads(data)
        p_paccesstoken = dataD['p_paccesstoken'].encode('utf-8')
        p_daccesstoken = dataD['p_daccesstoken'].encode('utf-8')
        p_title = dataD['p_title'].encode('utf-8')
        p_prescription = dataD['p_prescription'].encode('utf-8')
        cur = mysql.connection.cursor()
        cur.execute(
            '''INSERT INTO prescription(p_paccesstoken,p_daccesstoken,p_title,p_prescription) VALUES(%s,%s,%s,%s)''',
            (p_paccesstoken, p_daccesstoken, p_title, p_prescription))
        mysql.connection.commit()
        return jsonify(status=True, message="Prescription saved successfully.")


def allowed_file(filename):
    return '.' in filename and \
           filename.rsplit('.', 1)[1].lower() in ALLOWED_EXTENSIONS


if __name__ == '__main__':
    app.run(host='192.168.43.140', port=5000)
