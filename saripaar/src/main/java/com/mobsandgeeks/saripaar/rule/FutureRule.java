/*
 * Copyright (C) 2014 Mobs & Geeks
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mobsandgeeks.saripaar.rule;

import com.mobsandgeeks.saripaar.AnnotationRule;
import com.mobsandgeeks.saripaar.annotation.Future;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Ragunath Jawahar {@literal <rj@mobsandgeeks.com>}
 * @since 2.0
 */
public class FutureRule extends AnnotationRule<Future, Date> {

    private Calendar mSrcCalendar;
    private Calendar mDestCalendar;

    protected FutureRule(final Future future) {
        super(future);
    }

    @Override
    public boolean isValid(final Date data) {
        boolean strict = mRuleAnnotation.strict();
        Future.Precision precision = mRuleAnnotation.precision();
        int offset = mRuleAnnotation.offset();

        Calendar currentCalendar = new GregorianCalendar();

        Calendar passedCalendar = new GregorianCalendar();
        passedCalendar.setTime(data);

        switch (precision) {
            case DAY:
                mDestCalendar = new GregorianCalendar(currentCalendar.get(Calendar.YEAR),
                        currentCalendar.get(Calendar.MONTH), currentCalendar.get(Calendar.DAY_OF_MONTH));
                mSrcCalendar = new GregorianCalendar(passedCalendar.get(Calendar.YEAR),
                        passedCalendar.get(Calendar.MONTH), passedCalendar.get(Calendar.DAY_OF_MONTH));
                mDestCalendar.add(Calendar.DAY_OF_MONTH, offset);
                break;
            case MONTH:
                mDestCalendar = new GregorianCalendar(currentCalendar.get(Calendar.YEAR),
                        currentCalendar.get(Calendar.MONTH), 1);
                mSrcCalendar = new GregorianCalendar(passedCalendar.get(Calendar.YEAR),
                        passedCalendar.get(Calendar.MONTH), 1);
                mDestCalendar.add(Calendar.MONTH, offset);
                break;
            case YEAR:
                mDestCalendar = new GregorianCalendar(currentCalendar.get(Calendar.YEAR), 1, 1);
                mSrcCalendar = new GregorianCalendar(passedCalendar.get(Calendar.YEAR), 1, 1);
                mDestCalendar.add(Calendar.YEAR, offset);
                break;
        }

        long diff = mSrcCalendar.getTimeInMillis() - mDestCalendar.getTimeInMillis();

        return strict ? diff > 0 : diff >= 0;
    }
}
