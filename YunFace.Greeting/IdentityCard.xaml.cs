using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using YunFace.Greeting.Models;

namespace YunFace.Greeting
{
    public partial class IdentityCard : UserControl
    {
        public IdentityCard()
        {
            InitializeComponent();
        }

        public void Initialize(IdentifyingHistory identifyingHistory)
        {
            identityAvatar.Source = AppHelper.GetBitmapSource(identifyingHistory.Identity.PhotoUrl, AppHelper.FaceLibraryUrlRoot);

            var displayName = identifyingHistory.Identity.Name;
            var mask = AppHelper.IdentityMask;
            if (!string.IsNullOrEmpty(mask))
            {
                if (displayName.Length == 2)
                {
                    displayName = displayName.Substring(0, 1) + mask;
                }
                else if (displayName.Length > 2)
                {
                    for (var i = 0; i < displayName.Length - 2; i++)
                    {
                        mask += AppHelper.IdentityMask;
                    }
                    displayName = displayName.Substring(0, 1) + mask + displayName.Substring(displayName.Length - 1, 1);
                }
            }
            identityName.Text = displayName;
            identityCompany.Text = identifyingHistory.Identity.Company;
            identityTime.Text = identifyingHistory.LogTime.ToString(AppHelper.TimeFormat);

            // 相似度
            double similarity;
            if (AppHelper.IdentitySimilarity && double.TryParse(identifyingHistory.Identity.EXT_2, out similarity))
            {
                identitySimilarity.Text = similarity.ToString("P2");
            }
            else
            {
                identitySimilarity.Text = string.Empty;
            }

            // 黑名单
            if (!string.IsNullOrWhiteSpace(identifyingHistory.Identity.EXT_4)
                && (identifyingHistory.Identity.EXT_4.Trim() == "1" || identifyingHistory.Identity.EXT_4.ToLower().Trim() == "true"))
            {
                identityTag.Fill = new SolidColorBrush(Colors.Red);
            }
            else
            {
                identityTag.Fill = new SolidColorBrush(Colors.GreenYellow);
            }
        }
    }
}
